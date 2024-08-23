package ar.edu.austral.inf.sd

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import kotlin.random.Random


@Component
class ApiServicesImpl: RegisterNodeApiService, RelayApiService, PlayApiService {

    @Value("\${server.port:8080}")
    private val myServerPort: Int = 0
    private val nodes: MutableList<RegisterResponse> = mutableListOf()
    private fun myServerName() = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.serverName

    override fun registerNode(host: String?, port: Int?, name: String?): RegisterResponse {

        val nextNode = if (nodes.isEmpty()) {
            // es el primer nodo
            val me = RegisterResponse(myServerName(), myServerPort, "", "")
            nodes.add(me)
            me
        } else {
            nodes.last()
        }
        val uuid = UUID.randomUUID().toString()
        val salt = Base64.getEncoder().encodeToString(Random.nextBytes(9))

        val node = RegisterResponse(host!!, port!!, uuid, salt)
        nodes.add(node)

        return RegisterResponse(nextNode.nextHost, nextNode.nextPort, uuid, salt)
    }

    override fun relayMessage() {
        TODO("Not yet implemented")
    }

    override fun sendMessage(body: Any): PlayResponse {

        return PlayResponse()
    }

}