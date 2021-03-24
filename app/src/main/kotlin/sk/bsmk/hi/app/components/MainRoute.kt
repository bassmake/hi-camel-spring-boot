package sk.bsmk.hi.app.components

import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class MainRoute(val latch: CountDownLatch) : EndpointRouteBuilder() {

    override fun configure() {
        from("timer://input-timer?period=200&repeatCount=${latch.count}")
            .process { exchange ->
                exchange.`in`.body = "Started"
            }
            .to("http://programmingexcuses.com")
            .to("log:info")
            .process { latch.countDown() }
    }

}
