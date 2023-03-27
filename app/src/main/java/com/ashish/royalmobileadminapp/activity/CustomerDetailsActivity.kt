package com.ashish.royalmobileadminapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.data.auth.Customer
import com.ashish.royalmobileadminapp.databinding.ActivityCustomerDetailsBinding

class CustomerDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCustomerDetailsBinding

   var customer : Customer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
        setUpData()
        setContentView(binding.root)
    }

    private fun setUpData() {
        if (customer!=null){
            with(binding){
                txtCustomerEmail.text = customer!!.email
                txtCustomerFirstName.text = customer!!.first_name
                txtCustomerLastName.text = customer!!.last_name
                txtCustomerPhoneNumber.text = customer!!.phone_no.toString()
                txtCustomerHomeAddress.text = customer!!.cust_Address
                txtCustomerDeliveryAddress.text = customer!!.delivery_address
                txtCustomerAreaPincode.text = customer!!.pincode.toString()
            }
        }else{
            Toast.makeText(this@CustomerDetailsActivity, "Data not found some problem", Toast.LENGTH_SHORT).show()
        }
    }
}

























