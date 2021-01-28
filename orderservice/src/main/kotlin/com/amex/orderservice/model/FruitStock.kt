package com.amex.orderservice.model

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column

/*
 Entity class of table 'fruitstock'
 */
@Entity
@Table(name="fruitstock")
public class FruitStock(
	//The Id
    @Id
    val id: Long = -1,
 
	//The Column fruitname
    @Column(name = "fruitname")
    val fruitName: String = "",
 
	//The Colum  stockcount
    @Column(name = "stockcount")
    var stockCount: Int = 0  
){
  override fun toString(): String{
        return "FruitStock[id=${id}, fruitName=${fruitName}, stockCount=${stockCount}]"
  }
}