package com.amex.orderservice

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import kotlin.test.assertNotNull
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/*
 Spring boot test for Order Service Controller
 Tests the normal pricing of fruits with various combination
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceControllerTest {
	
	@Autowired
	lateinit var restTemplate : TestRestTemplate
	
	@Test
	fun testOrderService1() {
		
		val fruitsList : String = "Apple, Apple, Orange, Apple"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "2.05$")
	}
	
	@Test
	fun testOrderService2() {
		
		val fruitsList : String = ""
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
		assertEquals(result.body, "0.0$")
	}
	
	@Test
	fun testOrderService3() {
		
		val fruitsList : String = "APPLE, APPLE, ORANGE, APPLE"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "2.05$")
	}
	
	@Test
	fun testOrderService4() {
		
		val fruitsList : String = "ApPLe, APPLE, ORanGE, APPLE"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "2.05$")
	}
	
	@Test
	fun testOrderService5() {
		
		val fruitsList : String = "Mango, Banana"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.0$")
	}
	
	@Test
	fun testOrderService6() {
		
		val fruitsList : String = "Apple, Apple"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "1.2$")
	}
	
	@Test
	fun testOrderService7() {
		
		val fruitsList : String = "Orange, Orange, Orange"
		val result = restTemplate.getForEntity("/order?fruits=" + fruitsList, String::class.java)
		assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "0.75$")
	}
}