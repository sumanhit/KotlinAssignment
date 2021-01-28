package com.amex.orderservice.db.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.beans.factory.annotation.Autowired
import com.amex.orderservice.model.FruitStock
import com.amex.orderservice.db.repository.StockRepository

/*
 The service layer to access DB
 It holds methods to get the latest stock of fruits as well as update stock
 */
@Service
@Transactional
class StockService {

	//Injected StockService to communicate with DB
	@Autowired
	lateinit var repository: StockRepository
	
	//Updates the stock in DB after order
	fun updateStock(stock : FruitStock) {
		repository.save(stock)
	}
	
	//Get the latest inventory of specified fruit
	fun getLatestFruitStockCount(fruitName: String) : FruitStock{
		return repository.findByFruitName(fruitName).get(0)
	}
}