package com.amex.orderservice

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Value

/*
 Holds all the properties required for Order Service
 and fetches the values from application.propertie
 */
@Component
class OrderServiceProperties {
	
	@Value("\${fruit.apple.name}")
	lateinit var appleName : String
	
	@Value("\${fruit.orange.name}")
	lateinit var orangeName : String
	
	@Value("\${fruit.apple.price}")
	lateinit var applePrice : String
	
	@Value("\${fruit.orange.price}")
	lateinit var orangePrice : String
	
	@Value("\${dollar.sign}")
	lateinit var dollarSign : String
	
	@Value("\${fruits.input.seperator}")
	lateinit var fruitSeperator : String
	
	@Value("\${dollar.conversion.divisor}")
	lateinit var dollarDivisor : String 
	
}