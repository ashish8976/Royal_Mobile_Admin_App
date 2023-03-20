package com.ashish.royalmobileadminapp.data.product

data class Product(
    var product_id: Int? = null,
    var product_name: String? = null,
    var product_desc: String? = null,
    var cate_id: Int? = null,
    var productColor: List<ProductColor>? = null,
    var brand_id: Int? = null,
    var Mobile: List<Mobile>? = null,
    var Accessories: List<Accessories>? = null
) : java.io.Serializable
