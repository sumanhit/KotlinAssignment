package com.amex.orderservice.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.service.FruitPriceCalculatorService
import org.springframework.beans.factory.annotation.Qualifier

/*
 Rest Controller class which exposes
 APIs to service the requests and responses back to the caller
 */
@RestController
class OrderServiceController {

	//Injected FruitService with regular price
	@Autowired
	@Qualifier("fruitService")
	lateinit private var fruitPriceCalcService: FruitPriceCalculatorService

	//Injected FruitService with Offer price
	@Autowired
	@Qualifier("fruitOfferService")
	lateinit private var fruitPriceOfferCalcService: FruitPriceCalculatorService

	/* Rest Endpoint for order received with name of fruits to calculate normal price
 	   @Example URL - "/order/Apple,Apple,Orange,Apple"
 	   @Example Output - "2.05$"
 	*/
	@GetMapping("/order/{fruits}")
	fun getFruitPrice(@PathVariable("fruits") fruits: String): String {

		return fruitPriceCalcService.calculatePrice(fruits);
	}

	/* Rest Endpoint for order received with name of fruits to calculate offer price
 	   @Example URL - "/offer/order/Apple, Apple, Orange, Orange, Orange"
 	   @Example Output - "1.1$"
 	*/
	@GetMapping("/offer/order/{fruits}")
	fun getFruitOfferPrice(@PathVariable("fruits") fruits: String): String {

		return fruitPriceOfferCalcService.calculatePrice(fruits);
	}
}