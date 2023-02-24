package com.example.data.model

import java.time.LocalDate



data class Order(
    val order_id : String,
    val Email : String,
//    val cart_id :  Int,
    val order_date : LocalDate,
    val orderItems : List<OrderItem>?= null,
    val quantity : Int? = null,
    val trackingUrl : String?=null,
    val orderStatus : Int = 0,
    val paymentStatus : Int = 0,
    val total : Float,
    val discount : Float = 0.0f,
    val deliveryCharge : Float,
    val totalrecived : Float

)
