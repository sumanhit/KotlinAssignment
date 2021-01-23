package com.amex.orderservice.service

/**
Interface FruitPriceCalculatorService holds the contract
API designs which is required by the order Service
*/
interface FruitPriceCalculatorService {
	
	/**
	 This method calculates the price of fruits
	 @Input - List of Fruits Name
	 @Output - Price of fruits in dollar
	*/
	fun calculatePrice(fruitsListStr: String): String
}