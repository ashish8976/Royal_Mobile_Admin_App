package com.example.data.model


import java.security.Principal

data class Customer(
     val email : String,
     val password : String,
     val first_name : String,
     val last_name : String,
     val phone_no : Long,
     val cust_Address : String,
     val delivery_address : String,
     val pincode : Int
 ):java.io.Serializable