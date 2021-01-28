package com.amex.orderservice.exception

import java.lang.Exception

/*
 Custom Exception class to handle the event
 when stocks run out and notify with proper message
 */
class OutOfStockException(message: String) : Exception(message)