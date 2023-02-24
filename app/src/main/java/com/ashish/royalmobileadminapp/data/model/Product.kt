package com.example.data.model

data class Product(
    val product_id: Int,
    val product_name: String,
    val product_desc: String,
    val cate_name: String,
    val color: List<Color>,
    val brand_id: Int,
    var Mobile: List<Mobile>? = null,
    var Accessories: List<Accessories>? = null

)
