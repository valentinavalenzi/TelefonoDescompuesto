package ar.edu.austral.inf.sd

import ar.edu.austral.inf.sd.api.PlayApiService
import ar.edu.austral.inf.sd.api.RegisterNodeApiService
import ar.edu.austral.inf.sd.api.RelayApiService
import ar.edu.austral.inf.sd.model.PlayResponse
import ar.edu.austral.inf.sd.model.RegisterResponse
import ar.edu.austral.inf.sd.model.Signature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.security.MessageDigest
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.random.Random


@Component
class ApiServicesImpl: RegisterNodeApiService, RelayApiService, PlayApiService {

    @Value("\${server.name:nada}")
    private val myServerName: String = ""
    @Value("\${server.port:8080}")
    private val myServerPort: Int = 0
    private val nodes: MutableList<RegisterResponse> = mutableListOf()
    private val isServer
        get() = nodes.isNotEmpty()
    private val messageDigest = MessageDigest.getInstance("SHA-512")
    private val salt = newSalt()
    private val currentRequest
        get() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
    private var resultReady = CountDownLatch(1)
    private var currentMessageWaiting = MutableStateFlow<PlayResponse?>(null)
    private var currentMessageResponse = MutableStateFlow<PlayResponse?>(null)

    override fun registerNode(host: String?, port: Int?, name: String?): RegisterResponse {

        val nextNode = if (nodes.isEmpty()) {
            // es el primer nodo
            val me = RegisterResponse(currentRequest.serverName, myServerPort, "", "")
            nodes.add(me)
            me
        } else {
            nodes.last()
        }
        val uuid = UUID.randomUUID().toString()
        val node = RegisterResponse(host!!, port!!, uuid, newSalt())
        nodes.add(node)

        return RegisterResponse(nextNode.nextHost, nextNode.nextPort, uuid, newSalt())
    }

    override fun relayMessage(message: kotlin.String, signatures: kotlin.collections.List<Signature>?): Signature {
        val current = currentMessageWaiting.getAndUpdate { null }
        val receivedHash = doHash(message.encodeToByteArray(), salt)
        if (current != null) {
            // me llego algo, no lo tengo que pasar
            val response = current.copy(
                receivedHash = receivedHash,
                receivedLength = message.length,
                receivedContentType = currentRequest.contentType
            )
            currentMessageResponse.update { response }
            resultReady.countDown()
        }
        return Signature(myServerName, receivedHash)
    }

    override fun sendMessage(body: String): PlayResponse {
        currentMessageWaiting.update { newResponse(body) }
        sendRelayMessage(body, currentRequest.contentType)
        resultReady.await()
        resultReady = CountDownLatch(1)
        return currentMessageResponse.value!!
    }

    private fun sendRelayMessage(message: String, contentType: String) {
        if (isServer) {
            // a este nodo le tengo que mandar el mensaje
            val firstNode = nodes.last()
            // @ToDo acá laburen ustedes
        } else {
            // estoy solo, me lo automando
            // sleep(1000L)
            relayMessage(message, listOf())
        }
    }

    private fun newResponse(body: String) = PlayResponse(
        currentRequest.contentType,
        body.length,
        doHash(body.encodeToByteArray(), salt),
        currentRequest.contentType,
        body.length,
        doHash(body.encodeToByteArray(), salt),
    )

    private fun doHash(body: ByteArray, salt: String):  String {
        val saltBytes = Base64.getDecoder().decode(salt)
        messageDigest.update(saltBytes)
        val digest = messageDigest.digest(body)
        return Base64.getEncoder().encodeToString(digest)
    }

    companion object {
        fun newSalt(): String = Base64.getEncoder().encodeToString(Random.nextBytes(9))
    }
}