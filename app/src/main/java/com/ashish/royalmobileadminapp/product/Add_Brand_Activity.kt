package com.ashish.royalmobileadminapp.product

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddBrandBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants
import com.ashish.royalmobileadminapp.utils.Constants.brand
import com.example.data.model.Brand
import com.surajmanshal.mannsignadmin.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Add_Brand_Activity : AppCompatActivity() {

    lateinit var binding : ActivityAddBrandBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBrandBinding.inflate(layoutInflater)
        setContentView(binding.root)

       binding.addBrandButton.setOnClickListener {
            addBrandFunction()
           binding.edtBrandId.text?.clear()
           binding.edtBrandName.text?.clear()
       }
    }

    private fun addBrandFunction() {
        val brand_id = binding.edtBrandId.text.toString().toInt()
        val brand_name = binding.edtBrandName.text.toString()

        val data = Network_Service.networkInstance

        val sharedPreferences = getSharedPreferences(brand,Context.MODE_PRIVATE)

        val r = data.addbrand(Brand(brand_id = brand_id, brand_name = brand_name))

        r.enqueue(object : Callback<Simple_Response?> {
            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {
                val b = response.body()

                if (b != null) {
                    if (b.success) {
                        val s = sharedPreferences.edit()
                        s.putString(brand,brand_id.toString())
                        s.apply()
                        Toast.makeText(this@Add_Brand_Activity,"Brand Added Successfully",Toast.LENGTH_LONG).show()
                    }
                    else
                    {
                        Toast.makeText(this@Add_Brand_Activity,"Brand Not Added",Toast.LENGTH_LONG).show()
                    }
                }

                else
                {
                    Toast.makeText(this@Add_Brand_Activity,"Some Problem occur",Toast.LENGTH_LONG).show()
                }


            }

            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {


                binding.errorMsg.text = t.message.toString()
                Toast.makeText(this@Add_Brand_Activity , t.message.toString(),Toast.LENGTH_LONG).show()
            }
        })

    }
}