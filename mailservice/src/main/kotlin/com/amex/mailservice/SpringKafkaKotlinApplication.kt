package com.amex.mailservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka

/*
 Main class for Mail Service Application
 Using Spring Boot auto configuration
 */
@SpringBootApplication
@EnableKafka
class SpringKafkaKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringKafkaKotlinApplication>(*args)
}
