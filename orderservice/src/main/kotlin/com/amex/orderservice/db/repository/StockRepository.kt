package com.amex.orderservice.db.repository

import org.springframework.data.repository.CrudRepository
import com.amex.orderservice.model.FruitStock
import org.springframework.stereotype.Repository

/*
 The StockRepository implements CrudRepository of Spring data
 to query the DB
 */
@Repository
interface StockRepository: CrudRepository<FruitStock, Long>{
	
	//Query to DB using specified fruitname and returns the entity
    fun findByFruitName(fruitName: String): List<FruitStock>
} 