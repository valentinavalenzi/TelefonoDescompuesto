package ar.edu.austral.inf.sd

import org.springframework.stereotype.Component

@Component
class ApiServicesImpl: RegisterNodeApiService, RelayApiService, PlayApiService {
    override fun registerNode(host: String?, port: Int?, name: String?): RegisterResponse {
        // TODO("Not yet implemented $host $port $name")
        return RegisterResponse("hola",12, "uuu", "aaaa")
    }

    override fun relayMessage() {
        TODO("Not yet implemented")
    }

    override fun sendMessage(body: Any): PlayResponse {
        // TODO("Not yet implemented")
        return PlayResponse()
    }

}