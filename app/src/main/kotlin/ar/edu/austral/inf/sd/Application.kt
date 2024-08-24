package ar.edu.austral.inf.sd

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["ar.edu.austral.inf.sd", "ar.edu.austral.inf.sd.api", "ar.edu.austral.inf.sd.model"])
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
