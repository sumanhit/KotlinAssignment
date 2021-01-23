package com.amex.orderservice.service

import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.OrderServiceProperties
import org.springframework.stereotype.Service

/**
Class FruitPriceCalculator implements FruitPriceCalculator interface
and holds the business logic of calculating price of fruits.
APPLE price is 60 cents and ORANGE price is 25 cents
 */
@Service("fruitOfferService")
class FruitPriceOfferCalcServiceImpl : FruitPriceCalculatorService {

	@Autowired
	lateinit var orderServiceProp : OrderServiceProperties
	
	@Override
	override fun calculatePrice(fruitsListStr: String): String {

		// Split the console input using comma, trim and converts to upper case 
		val fruitsList: List<String> =
			fruitsListStr.split(orderServiceProp.fruitSeperator)
				.map { str -> str.trim() }
				.map { str -> str.toUpperCase() }

		var noOfApples: Int = 0
		
		var noOfOranges: Int =0
		
		//Cost of fruits in dollar
		var costInDollar: Double
		//Looping through the list of fruits
		for (fruit in fruitsList) {
			//Check if apple or orange
			if (fruit.equals(orderServiceProp.appleName)) {
				noOfApples++
				
			} else if (fruit.equals(orderServiceProp.orangeName)) {
				noOfOranges++
				
			} else {
				// Log Warning message
			}
		}
		//Cost of fruits in cent
		var costInCent = ((noOfApples/2) + (noOfApples%2))*Integer.valueOf(orderServiceProp.applePrice)
		
		costInCent += (((noOfOranges/3)*2) + (noOfOranges%3))*Integer.valueOf(orderServiceProp.orangePrice)
		//Calculate dollar output
		costInDollar = costInCent / (orderServiceProp.dollarDivisor).toDouble()

		return costInDollar.toString() + orderServiceProp.dollarSign
	}
}