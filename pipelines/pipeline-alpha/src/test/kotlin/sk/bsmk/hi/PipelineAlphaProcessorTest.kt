package sk.bsmk.hi

import org.apache.camel.test.junit5.CamelTestSupport
import org.apache.camel.builder.RouteBuilder

import org.apache.camel.RoutesBuilder
import org.junit.jupiter.api.Test

class PipelineAlphaProcessorTest : CamelTestSupport() {

    @Test
    fun testMock() {
        getMockEndpoint("mock:result").expectedBodiesReceived("HELLO WORLD")
        template.sendBody("direct:start", "Hello World")
        assertMockEndpointsSatisfied()
    }

    override fun createRouteBuilder(): RoutesBuilder {
        return object : RouteBuilder() {
            override fun configure() {
                from("direct:start")
                    .process(PipelineAlphaProcessor)
                    .to("mock:result")
            }
        }
    }

}