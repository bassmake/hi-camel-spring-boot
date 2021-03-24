package sk.bsmk.hi.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

@ConstructorBinding
@ConfigurationProperties("app")
data class AppProperties(val count: Int)