package com.amex.orderservice.service

/**
Interface FruitPriceCalculatorService holds the contract
API designs which is required by the order Service
*/
interface FruitPriceCalculatorService {
	
	/**
	 This method calculates the price of fruits
	 @Input - No. Of Apples and No. of Oranges
	 @Output - Price of fruits in dollar
	*/
	fun calculatePrice(noOfApples: Int, noOfOranges: Int): String
}