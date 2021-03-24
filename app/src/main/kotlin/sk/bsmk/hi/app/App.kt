package sk.bsmk.hi.app

import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@SpringBootApplication
class App

const val count: Int = 10

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

@Component
class MainRoute(val latch: CountDownLatch) : EndpointRouteBuilder() {

    override fun configure() {
        from("timer://input-timer?period=200&repeatCount=$count")
            .process { exchange ->
                exchange.`in`.body = "Started"
            }
            .to("http://programmingexcuses.com")
            .to("log:info")
            .process { latch.countDown() }
    }

}
