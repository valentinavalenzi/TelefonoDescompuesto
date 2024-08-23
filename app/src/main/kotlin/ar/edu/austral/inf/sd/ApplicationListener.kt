package ar.edu.austral.inf.sd

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment
import org.springframework.http.server.ServerHttpRequest
import org.springframework.stereotype.Component
import java.net.InetAddress

@Component
class ApplicationListener {
    @Autowired
    lateinit var apiServices: ApiServicesImpl

    @Autowired
    lateinit var environment: Environment

    @Value("\${server.port:8080}")
    val myPort: Int = 0

    @EventListener(ApplicationReadyEvent::class)
    fun onApplicationReady(event: ApplicationReadyEvent) {
        //
        val local= InetAddress.getLocalHost()
//        val myData = apiServices.registerNode("pepe", myPort, "<me>", ServerHttpRequest())
//        println(myData)
    }
}