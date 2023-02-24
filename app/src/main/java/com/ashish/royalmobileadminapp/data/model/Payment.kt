package com.example.data.model

import java.time.LocalDate

data class Payment(
    val payment_id : String,
    val emailId : String,
    val payment_method : Int,
    val delivery_charge : Float,
    val date : LocalDate,
    val amount : Float,
    val orderId : String
)
