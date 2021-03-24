package sk.bsmk.hi.app.components

import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.springframework.stereotype.Component
import java.util.concurrent.CountDownLatch

@Component
class MainRoute(val latch: CountDownLatch) : EndpointRouteBuilder() {

    companion object {
        val regex = Regex("<a href=\"/\" rel=\"nofollow\" style=\"text-decoration: none; color: #333;\">(.*)</a>")
    }

    override fun configure() {
        from("timer://input-timer?period=200&repeatCount=${latch.count}")
            .to("metrics:counter:input.counter")
            .to("log:before-http-call?showHeaders=true")
            .to("metrics:timer:http-call.timer?action=start")
            .to("http://programmingexcuses.com/")
            .to("metrics:timer:http-call.timer?action=stop")
            .convertBodyTo(String::class.java)
            .to("log:after-http-call?showBody=true&showProperties=true")
            .process { exchange ->
                val body = exchange.`in`.getMandatoryBody(String::class.java)
                val extracted = regex.find(body)?.groups?.get(1)
                exchange.`in`.body = extracted?.value
            }
            .to("log:after-extraction?multiline=true&showProperties=true")
            .process { latch.countDown() }
    }

}
