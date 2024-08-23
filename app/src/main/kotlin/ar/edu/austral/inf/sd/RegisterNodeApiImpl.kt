package ar.edu.austral.inf.sd

import org.springframework.stereotype.Component

@Component
class RegisterNodeApiImpl: RegisterNodeApiService {
    override fun registerNodePost(host: String?, port: Int?, name: String?): RegisterNodePost200Response {
        // TODO("Not yet implemented $host $port $name")
        return RegisterNodePost200Response("hola",12, "uuu", "aaaa")
    }

}