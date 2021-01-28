package com.amex.mailservice

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

/*
 The order consumer class acts as a kafka consumer using default configuration
 It listens to the topic and whenever the messages received it prints in the log
 */
@Component
class OrderConsumer {

	companion object {
		const val TOPIC_NAME = "order_submitted"
		const val GROUP_NAME = "order_consumer"
	}

	// The logger instance
	private val LOGGER = LoggerFactory.getLogger(javaClass)

	//The Kafka listener method
	@KafkaListener(topics = [TOPIC_NAME], groupId = GROUP_NAME)
	fun processMessage(message: String) {
		
		LOGGER.info("Order Status: {}", message)
	}
}
