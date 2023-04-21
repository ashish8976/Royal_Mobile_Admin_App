package com.ashish.royalmobileadminapp.product


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.data.product.Brand
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddBrandBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants.brand
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

          // val id = binding.edtBrandId.toString()
           val name = binding.edtBrandName.toString()
           if (name.isEmpty()){
               Toast.makeText(this@Add_Brand_Activity,"Enter the name",Toast.LENGTH_LONG).show()
           }else{
               addBrandFunction()
           }
       }
    }



  private fun addBrandFunction() {
      //  val brand_id = binding.edtBrandId.text.toString().toInt()
        val brand_name = binding.edtBrandName.text.toString()
        val data = Network_Service.networkInstance
       // val sharedPreferences = getSharedPreferences(brand,Context.MODE_PRIVATE)
       // val r =
      CoroutineScope(Dispatchers.IO).launch {
          data.addbrand(Brand(brand_name = brand_name)).apply {
              if (this.success){
                  withContext(Dispatchers.Main){
                      Toast.makeText(this@Add_Brand_Activity, "Data Added", Toast.LENGTH_SHORT).show()
                  }
              }
              else{
                  var a = this.message
                  withContext(Dispatchers.Main){
                      Toast.makeText(this@Add_Brand_Activity,a, Toast.LENGTH_SHORT).show()
                  }
              }
          }
      }


    }
}