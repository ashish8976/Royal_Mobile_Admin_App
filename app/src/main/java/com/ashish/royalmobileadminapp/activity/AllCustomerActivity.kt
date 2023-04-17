package com.ashish.royalmobileadminapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.adapter.AllCustomerAdapter
import com.ashish.royalmobileadminapp.databinding.ActivityAllCustomerBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.example.data.model.Customer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllCustomerActivity : AppCompatActivity() {

    lateinit var binding : ActivityAllCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCustomerBinding.inflate(layoutInflater)
        loadCustomer()
        setContentView(binding.root)
    }

    companion object{
        val db = Network_Service.networkInstance
    }

    private fun loadCustomer(){
        val c = db.getAllCustomer()
        c.enqueue(object : Callback<List<Customer>?> {
            override fun onResponse(
                call: Call<List<Customer>?>,
                response: Response<List<Customer>?>
            ) {
                if (response.body() != null){
                    binding.allcustomerRecycleView.adapter =
                        AllCustomerAdapter(this@AllCustomerActivity,response.body()!!)
                }
                else{
                    Toast.makeText(this@AllCustomerActivity, "Response is null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Customer>?>, t: Throwable) {
                Toast.makeText(this@AllCustomerActivity, "Fail $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}