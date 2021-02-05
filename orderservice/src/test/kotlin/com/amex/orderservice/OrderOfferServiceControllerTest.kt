package com.amex.orderservice

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import kotlin.test.assertNotNull
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import org.springframework.test.context.TestPropertySource

/*
 Spring boot test for Order Service Controller
 Tests the offer pricing of fruits with various combination
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties=["offer.enabled=true"])
class OrderOfferServiceControllerTest {
	
	@Autowired
	lateinit var restTemplate : TestRestTemplate
	
	@Test
	fun testOfferPrice1() {
		
		val fruitsList : String = "Apple, Apple, Orange, Orange, Orange"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "1.1$")
	}
	
	@Test
	fun testOfferPrice2() {
		
		val fruitsList : String = "Orange, Orange, Orange"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.5$")
	}
	
	@Test
	fun testOfferPrice3() {
	
		val fruitsList : String = "Apple, Apple"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.6$")
	}
	
	@Test
	fun testOfferPrice4() {
		
		val fruitsList : String = "Apple"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.6$")
	}
	
	@Test
	fun testOfferPrice5() {
		
		val fruitsList : String = "Apple, Apple, Apple, Apple"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "1.2$")
	}
	
	@Test
	fun testOfferPrice6() {
		
		val fruitsList : String = "Orange, Orange, Orange"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.5$")
	}
	
	@Test
	fun testOfferPrice7() {
		
		val fruitsList : String = "Orange"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.25$")
	}
	
	@Test
	fun testOfferPrice8() {
		
		val fruitsList : String = "Orange, Orange"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.5$")
	}
}