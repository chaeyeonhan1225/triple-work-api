package com.interpark.tripleworkapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class TripleWorkApiApplication

fun main(args: Array<String>) {
    runApplication<TripleWorkApiApplication>(*args)
}
