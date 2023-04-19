package com.ashish.royalmobileadminapp.data.product

import com.example.data.model.Product

data class OrderItem(
    var product: Product? = null,
    var quantity : Int,
    var totalPrice :Float
)
