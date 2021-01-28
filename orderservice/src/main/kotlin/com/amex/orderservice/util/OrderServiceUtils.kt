package com.amex.orderservice.util

import java.util.HashMap
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.config.OrderServiceConfig

/* Utility class of orderservice
 which holds companion methods
 */
class OrderServiceUtils {

	companion object {

		/*
 		This method converts the list of fruits string to Map of fruits with count
 		@Input - Apple,Apple,Orange,Apple
 		@Output - <Apple, 3>, <Orange, 1>
 		*/
		fun getNoOfFruits(fruitsList: List<String>, appleName: String, orangeName: String): Map<String, Int> {

			var noOfApples: Int = 0
			var noOfOranges: Int = 0
			var fruitsCountMap = HashMap<String, Int>()

			for (fruit in fruitsList) {
				//Check if apple or orange
				if (fruit.equals(appleName)) {
					noOfApples++

				} else if (fruit.equals(orangeName)) {
					noOfOranges++

				} else {
					// Log Warning message
				}
			}
			
			fruitsCountMap.put(appleName, noOfApples)
			fruitsCountMap.put(orangeName, noOfOranges)
			
			return fruitsCountMap
		}
		
		/*This method calculates the no of delivery days based on abosolute offer price in dollar, minimum 1
 		@Input - 1.2$, Output - 1
 		@Input - 0.7$, Output - 1
 		@Input - 2.3$, Output - 2
 		*/
		fun estimateDeliveryDays(fruitPrice: String): Int {

			var dollarAbs = Integer.valueOf(fruitPrice.substring(0, fruitPrice.indexOf(".")))
			return if (dollarAbs > 0) dollarAbs else 1
		}
	}
}