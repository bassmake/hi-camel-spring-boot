package sk.bsmk.hi.app

import org.apache.camel.CamelContext
import org.apache.camel.spring.boot.CamelContextConfiguration
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Configuration
class AppConfig {

    @Bean
    fun countDownLatch(): CountDownLatch {
        return CountDownLatch(count)
    }

    @Bean
    fun camelContextConfiguration(latch: CountDownLatch): CamelContextConfiguration {
        return object : CamelContextConfiguration {
            private val log = LoggerFactory.getLogger(this.javaClass)
            override fun beforeApplicationStart(camelContext: CamelContext?) {
            }

            override fun afterApplicationStart(camelContext: CamelContext?) {
                latch.await(10, TimeUnit.SECONDS)
                if (latch.count == 0L) {
                    log.info("Received $count notifications. Shutting down!")
                } else {
                    log.error("Still not received ${latch.count} notifications. Shutting down!")
                }
                camelContext!!.stop()
            }
        }
    }

}