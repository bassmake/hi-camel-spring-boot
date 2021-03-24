package sk.bsmk.hi.app.routes

import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.springframework.stereotype.Component

@Component
class MainRoute : EndpointRouteBuilder() {

    override fun configure() {
        from("timer://input-timer?repeatCount=10")
            .to("http://programmingexcuses.com")
            .to("log:info")
    }

}