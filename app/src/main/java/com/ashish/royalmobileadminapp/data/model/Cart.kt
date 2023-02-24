package com.example.data.model

data class Cart(
    val cart_id : Int,
    val email : String,
    val product_id : Int,
    val quentity : Int,
    val total_price : Float
)
