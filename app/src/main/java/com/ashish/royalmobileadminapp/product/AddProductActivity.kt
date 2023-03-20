package com.ashish.royalmobileadminapp.product

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.Brand
import com.ashish.royalmobileadminapp.data.product.Mobile
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.data.product.ProductColor
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddProductBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import kotlinx.coroutines.selects.select
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    val arr = mutableListOf<Brand>()
    var mProduct = Product()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //intent.getSerializableExtra("product") as Product
        Toast.makeText(this, "$mProduct", Toast.LENGTH_SHORT).show()

        getAllBrandSpinner()

        binding.addMobiles.setOnClickListener {
            val intent = Intent(this, AddMobileActivity::class.java)
            intent.putExtra("pId",binding.edtProductId.text.toString())
            startActivityForResult(intent, 2)
        }
        binding.addAccessories.setOnClickListener {
            val intent = Intent(this, AddAccessroiesActivity::class.java)
            intent.putExtra("pId",binding.edtProductId.text.toString())
            startActivityForResult(intent, 3)
        }
        binding.addProductButton.setOnClickListener {
            setupProduct()
            println("$mProduct")
            addProduct()
        }
    }
    private fun setupProduct() {
        mProduct.apply {
            binding.apply {
                product_id = edtProductId.text.toString().toInt()
                product_name = edtproductName.text.toString()
                product_desc = edtProductDesc.text.toString()
                cate_name = edtproductCategory.selectedItem.toString()
                val BrandName = arr.filter { it.brand_name==edtproductBrandname.selectedItem.toString()}.first().brand_id
                brand_id = BrandName
//                val I'd = arr.filter{it.name=selected}.id
               // brand_id = edtproductBrandname.selectedItemId.toInt()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
//                Toast.makeText(
//                    this@AddProductActivity,
//                    "${data?.getSerializableExtra("mobile")}",
//                    Toast.LENGTH_LONG
//                ).show()
                setupProduct()
                mProduct.Mobile = listOf(data?.getSerializableExtra("mobile") as Mobile)
                mProduct.productColor = listOf(data.getSerializableExtra("colors") as ProductColor)
                Toast.makeText(this, "$mProduct", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(
                    this@AddProductActivity,
                    "hii",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun addProduct() {
        val x = Network_Service.networkInstance.add_product(mProduct)
        x.enqueue(object : Callback<Simple_Response?> {
            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {
                response.body()?.let {
                    Toast.makeText(this@AddProductActivity,it.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                Toast.makeText(this@AddProductActivity, "Some Problem occur", Toast.LENGTH_SHORT).show()
            }
        })
    }


   private fun getAllBrandSpinner()
   {
       val a = Network_Service.networkInstance.getBrand()
       a.enqueue(object : Callback<List<Brand>?> {
           override fun onResponse(call: Call<List<Brand>?>, response: Response<List<Brand>?>) {

               val b = response.body()!!
               val are = b.map {
                   it.brand_name
               }
               val adp = ArrayAdapter(this@AddProductActivity,R.layout.simple_spinner_brand_name,are)
               binding.edtproductBrandname.adapter = adp
           }

           override fun onFailure(call: Call<List<Brand>?>, t: Throwable) {
               Toast.makeText(this@AddProductActivity, "Some Problem occur", Toast.LENGTH_SHORT).show()
           }
       })
   }


}

