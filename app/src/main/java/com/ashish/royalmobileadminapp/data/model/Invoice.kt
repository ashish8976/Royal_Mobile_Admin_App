package com.example.data.model

data class Invoice(
    val invoice_id:Int,
    val date : String,
    val discount : Int,
    val delivery_charge : Int,
    val invoice_details: String
)
