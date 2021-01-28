package com.amex.orderservice.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.service.FruitPriceCalculatorService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.RequestParam
import org.apache.kafka.clients.admin.NewTopic
import com.amex.orderservice.exception.OutOfStockException
import com.amex.orderservice.config.OrderServiceConfig
import com.amex.orderservice.util.OrderServiceUtils

/*
 Rest Controller class which exposes
 APIs to service the requests and responses back to the caller
 The controller also posts messages to Kafka topic to update the order status
 */
@RestController
class OrderServiceController {

	//KafkaTemplate dependency injected
	@Autowired
	lateinit private var kafkaTemplate: KafkaTemplate<String, String>

	//OrderServiceRequestHandler dependency injected 
	@Autowired
	lateinit private var orderRequestHandler: OrderServiceRequestHandler
	
	//OrderServiceConfig dependency injected 
	@Autowired
	lateinit var orderServiceProp : OrderServiceConfig

	/* Rest Endpoint for order received with name of fruits to calculate normal price
 	   and post order update to Kafka
 	   @Example URL - "/order?fruits=Apple,Apple,Orange,Apple"
 	   @Example Output - "2.05$"
	 */
	@GetMapping("/order")
	fun getFruitPrice(@RequestParam fruits: String): String {
		var totalPrice: String = "0.0$"
		try {
			totalPrice = orderRequestHandler.handleOrder(fruits, false)
			val deliveryDays = OrderServiceUtils.estimateDeliveryDays(totalPrice)
			kafkaTemplate.send(
				orderServiceProp.orderTopicName,
				"Order is placed successfully for [" + fruits + "]. Total Price: " + totalPrice + ". Estimated Delivery time : " + deliveryDays + " days." 
			)
		} catch (e: OutOfStockException) {
			kafkaTemplate.send(
				orderServiceProp.orderTopicName,
				e.message
			)
		}

		return totalPrice
	}

	/* Rest Endpoint for order received with name of fruits to calculate offer price
 	   and post order update to Kafka
 	   @Example URL - "/offer/order?fruits=Apple, Apple, Orange, Orange, Orange"
 	   @Example Output - "1.1$"
	 */
	@GetMapping("/offer/order")
	fun getFruitOfferPrice(@RequestParam fruits: String): String {
		var totalPrice: String = "0.0$"
		try {
			totalPrice = orderRequestHandler.handleOrder(fruits, true)
			val deliveryDays = OrderServiceUtils.estimateDeliveryDays(totalPrice)
			kafkaTemplate.send(
				orderServiceProp.orderTopicName,
				"Order is placed successfully with offer for [" + fruits + "]. Total Offer Price: " + totalPrice + ". Estimated Delivery time : " + deliveryDays + " days."
			)
		} catch (e: OutOfStockException) {
			kafkaTemplate.send(
				orderServiceProp.orderTopicName,
				e.message
			)
		}
		return totalPrice
	}
}