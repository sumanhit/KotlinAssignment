package com.amex.orderservice.service

import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.config.OrderServiceConfig
import org.springframework.stereotype.Service

/**
Class FruitPriceCalculator implements FruitPriceCalculator interface
and holds the business logic of calculating price of fruits.
APPLE price is 60 cents and ORANGE price is 25 cents
 */
@Service("fruitOfferService")
class FruitPriceOfferCalcServiceImpl : FruitPriceCalculatorService {

	@Autowired
	lateinit var orderServiceProp: OrderServiceConfig

	/*
 	Calculates the total price in cents and return dollar value
 	APPLE price - 60 cents and ORANGE price - 25 cents
 	APPLE OFFER - BUY 1 & GET 1
 	ORANGE OFFER - BUY 3 at price of 2
 	*/
	@Override
	override fun calculatePrice(noOfApples: Int, noOfOranges: Int): String {
		
		//Cost of fruits in dollar
		var costInDollar: Double

		//Cost of fruits in cent
		var costInCent = ((noOfApples / 2) + (noOfApples % 2)) * Integer.valueOf(orderServiceProp.applePrice)

		costInCent += (((noOfOranges / 3) * 2) + (noOfOranges % 3)) * Integer.valueOf(orderServiceProp.orangePrice)
		//Calculate dollar output
		costInDollar = costInCent / (orderServiceProp.dollarDivisor).toDouble()

		return costInDollar.toString() + orderServiceProp.dollarSign
	}
}