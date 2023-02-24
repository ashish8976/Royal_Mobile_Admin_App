package com.example.data.model

data class OrderItem(
    var product: Product? = null,
    var quantity : Int,
    var totalPrice :Float
)
