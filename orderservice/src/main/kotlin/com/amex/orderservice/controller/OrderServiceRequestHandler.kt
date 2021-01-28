package com.amex.orderservice.controller

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.config.OrderServiceConfig
import org.springframework.beans.factory.annotation.Qualifier
import com.amex.orderservice.exception.OutOfStockException
import com.amex.orderservice.util.OrderServiceUtils
import com.amex.orderservice.model.FruitStock
import com.amex.orderservice.service.FruitPriceCalculatorService
import com.amex.orderservice.db.service.StockService

/*
 This class contains the business logic to handle the order request
 and get invoked from the controller
 */
@Component
class OrderServiceRequestHandler {

	//Injected Configuration bean
	@Autowired
	lateinit var orderServiceProp: OrderServiceConfig

	//Injected StockService to communicate with DB
	@Autowired
	lateinit var stockService: StockService

	//Injected FruitService with regular price
	@Autowired
	@Qualifier("fruitService")
	lateinit private var fruitPriceCalcService: FruitPriceCalculatorService

	//Injected FruitService with Offer price
	@Autowired
	@Qualifier("fruitOfferService")
	lateinit private var fruitPriceOfferCalcService: FruitPriceCalculatorService

	/*
 	This method splits the fruits string and retrieves Apples and Oranges count
   	Then it query DB to check sufficient stock to service the request
 	If stocks are not present it generates custom exception to let handler know about OutOfStock
 	If stocks are present it updates the DB stock value
 	@Input - fruitsListStr - The Fruit list string
 			 isOffer - identified whether the offer needs to be applied
 	@Output - costInDollar - The Dollar price of the fruits
	 */
	fun handleOrder(fruitsListStr: String, isOffer: Boolean) : String {

		//Split the console input using comma, trim and converts to upper case 
		val fruitsList: List<String> =
			fruitsListStr.split(orderServiceProp.fruitSeperator)
				.map { str -> str.trim() }
				.map { str -> str.toUpperCase() }

		//Retrieves the count of fruits
		var fruitsCountMap: Map<String, Int> =
			OrderServiceUtils.getNoOfFruits(fruitsList, orderServiceProp.appleName, orderServiceProp.orangeName)
		var noOfApples: Int = fruitsCountMap.get(orderServiceProp.appleName)!!
		var noOfOranges: Int = fruitsCountMap.get(orderServiceProp.orangeName)!!

		//Check inventory
		var appleStock = stockService.getLatestFruitStockCount(orderServiceProp.appleName)
		var orangeStock = stockService.getLatestFruitStockCount(orderServiceProp.orangeName)

		if (noOfApples > appleStock.stockCount || noOfOranges > orangeStock.stockCount) {
			//Throw custom exception if unable to service request
			throw OutOfStockException("Sorry! Order is failed as items are out of stock. Available Apple in Stock - " + appleStock.stockCount + ". Available Orange in Stock - " + orangeStock.stockCount + ".")
		}
		
		//Update the DB if stocks are present
		appleStock.stockCount -= noOfApples
		orangeStock.stockCount -= noOfOranges
		stockService.updateStock(appleStock)
		stockService.updateStock(orangeStock)
		
		//Calculate the total price
		//Cost of fruits in dollar
		var costInDollar: String
		
		if(isOffer){
			costInDollar = fruitPriceOfferCalcService.calculatePrice(noOfApples, noOfOranges)
		} else {
			costInDollar = fruitPriceCalcService.calculatePrice(noOfApples, noOfOranges)
		}
		return costInDollar
	}

}