package ar.edu.austral.inf.sd

@SpringBootApplication
class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() {
    println(App().greeting)
}
