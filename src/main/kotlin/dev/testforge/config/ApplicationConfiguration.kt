package dev.testforge.config

import dev.testforge.logger
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.random.Random

@SpringBootConfiguration
@EnableScheduling
@EnableConfigurationProperties
class ApplicationConfiguration {

    private val log = logger<ApplicationConfiguration>()

    @Scheduled(fixedRate = 1, timeUnit = SECONDS)
    fun printLog() {
        val randomInt = randomNumber()
        if (randomInt % 50 == 0) {
            log.error("Response code ${errorCodes.random()} ${errorMessages.random()} Payload: $randomInt")
        } else if (randomInt % 10 == 0) {
            log.warn("Response code ${warnCodes.random()} ${warnMessages.random()} Payload: $randomInt")
        } else if (randomInt % 2 == 0) {
            log.info("Response code ${successCodes.random()} ${successMessages.random()} Payload: $randomInt")
        }
    }

    fun randomNumber() = Random.nextInt(0, 100)

    val successCodes = listOf(200, 201, 202, 204)
    val successMessages = listOf("Operation successful", "OK", "Completed successfully")
    val warnCodes = listOf(400, 401, 403)
    val warnMessages = listOf("Unexpected behaviour", "WARNING", "Recoverable exception")
    val errorCodes = listOf(404, 405, 408, 500, 502, 504)
    val errorMessages = listOf("ERROR", "Unrecoverable exception")
}