package dev.testforge

import dev.testforge.config.ApplicationConfiguration
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(ApplicationConfiguration::class)
class Application {

    private val log = logger<Application>()

    init {
        log.info("Started Logger Stub")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<Application>(*args)
        }
    }

}


inline fun <reified T> logger(): Log {
    return LogFactory.getLog(T::class.java)
}