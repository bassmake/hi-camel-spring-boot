package sk.bsmk.hi

import org.apache.camel.Exchange
import org.apache.camel.Processor

object PipelineAlphaProcessor : Processor {

    override fun process(exchange: Exchange) {
        val input  = exchange.message.getMandatoryBody(String::class.java)
        exchange.`in`.body = input.toUpperCase()
    }

}