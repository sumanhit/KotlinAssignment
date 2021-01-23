package com.amex.orderservice.service

import java.util.logging.Logger
import com.sun.org.slf4j.internal.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.OrderServiceProperties

/**
Class FruitPriceCalculator implements FruitPriceCalculator interface
and holds the business logic of calculating price of fruits.
APPLE price is 60 cents and ORANGE price is 25 cents
 */
@Service("fruitService")
class FruitPriceCalculatorServiceImpl : FruitPriceCalculatorService {

	@Autowired
	lateinit var orderServiceProp : OrderServiceProperties
	
	@Override
	override fun calculatePrice(fruitsListStr: String): String {

		// Split the console input using comma, trim and converts to upper case 
		val fruitsList: List<String> =
			fruitsListStr.split(orderServiceProp.fruitSeperator)
				.map { str -> str.trim() }
				.map { str -> str.toUpperCase() }

		//Cost of fruits in cent
		var costInCent: Int = 0
		//Cost of fruits in dollar
		var costInDollar: Double
		//Looping through the list of fruits
		for (fruit in fruitsList) {
			//Check if apple or orange
			if (fruit.equals(orderServiceProp.appleName)){
				costInCent += Integer.valueOf(orderServiceProp.applePrice)

			} else if (fruit.equals(orderServiceProp.orangeName)) {
				costInCent += Integer.valueOf(orderServiceProp.orangePrice)
			} else {
				// Log Warning message
			}
		}
				
		costInDollar = costInCent / (orderServiceProp.dollarDivisor).toDouble()
		
		return costInDollar.toString() + orderServiceProp.dollarSign
	}
}