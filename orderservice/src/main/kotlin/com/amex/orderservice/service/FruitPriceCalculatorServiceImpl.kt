package com.amex.orderservice.service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.config.OrderServiceConfig

/**
Class FruitPriceCalculator implements FruitPriceCalculator interface
and holds the business logic of calculating price of fruits.
 */
@Service("fruitService")
class FruitPriceCalculatorServiceImpl : FruitPriceCalculatorService {

	@Autowired
	lateinit var orderServiceProp : OrderServiceConfig
	
	/*
 	Calculates the total price in cents and return dollar value
 	APPLE price - 60 cents and ORANGE price - 25 cents
 	*/
	@Override
	override fun calculatePrice(noOfApples: Int, noOfOranges: Int): String {

		//Cost of fruits in cent
		var costInCent: Int = 0
		//Cost of fruits in dollar
		var costInDollar: Double
		
		costInCent += noOfApples*Integer.valueOf(orderServiceProp.applePrice)
		costInCent += noOfOranges*Integer.valueOf(orderServiceProp.orangePrice)
		costInDollar = costInCent / (orderServiceProp.dollarDivisor).toDouble()
		
		return costInDollar.toString() + orderServiceProp.dollarSign
	}
}