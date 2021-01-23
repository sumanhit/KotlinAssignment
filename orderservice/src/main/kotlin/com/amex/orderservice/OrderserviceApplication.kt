package com.amex.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.SpringApplication

/*
 Main class for Order Service Application
 Using Spring Boot auto configuration
 */
@SpringBootApplication
class OrderserviceApplication

fun main(args: Array<String>) {
	SpringApplication.run(OrderserviceApplication::class.java, *args)
}
