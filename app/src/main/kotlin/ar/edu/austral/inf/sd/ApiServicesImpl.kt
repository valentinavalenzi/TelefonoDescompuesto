package ar.edu.austral.inf.sd

import ar.edu.austral.inf.sd.api.PlayApiService
import ar.edu.austral.inf.sd.api.RegisterNodeApiService
import ar.edu.austral.inf.sd.api.RelayApiService
import ar.edu.austral.inf.sd.model.PlayResponse
import ar.edu.austral.inf.sd.model.RegisterResponse
import ar.edu.austral.inf.sd.model.Signature
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.security.MessageDigest
import java.util.*
import kotlin.random.Random


@Component
class ApiServicesImpl: RegisterNodeApiService, RelayApiService, PlayApiService {

    @Value("\${server.port:8080}")
    private val myServerPort: Int = 0
    private val nodes: MutableList<RegisterResponse> = mutableListOf()
    private val messageDigest = MessageDigest.getInstance("SHA-512")
    private val salt = newSalt()
    private fun request() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

    override fun registerNode(host: String?, port: Int?, name: String?): RegisterResponse {

        val nextNode = if (nodes.isEmpty()) {
            // es el primer nodo
            val me = RegisterResponse(request().serverName, myServerPort, "", "")
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

    override fun relayMessage(message: String, signature: String?) {
        // TODO("Not yet implemented")
    }

    override fun sendMessage(body: String): PlayResponse {
        val hash = doHash(body.encodeToByteArray(), salt)
//        println("body = ${body}")
//        println("bodyName = ${body.javaClass.name}")
        println("request().contentType = ${request().contentType}")
        return PlayResponse(hash)
    }

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