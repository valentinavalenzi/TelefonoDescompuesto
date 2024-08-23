package ar.edu.austral.inf.sd
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class App

fun main(args: Array<String>) {
    runApplication<App>(*args){
        setBannerMode(Banner.Mode.OFF)
    }
}
