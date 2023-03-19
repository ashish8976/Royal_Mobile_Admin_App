package com.ashish.royalmobileadminapp.product

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.data.product.Mobile
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.data.product.ProductColor
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddProductBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.example.data.model.Brand
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    var mProduct = Product()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //intent.getSerializableExtra("product") as Product
        Toast.makeText(this, "$mProduct", Toast.LENGTH_SHORT).show()

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
                cate_name = edtproductCategory.text.toString()
                brand_id = edtproductBrandname.text.toString().toInt()
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
//                TODO("Not yet implemented")
            }
        })
    }

    private suspend  fun fetchBrandName(){
        Network_Service.networkInstance.getBrand().enqueue(object : Callback<List<Brand>?> {
            override fun onResponse(call: Call<List<Brand>?>, response: Response<List<Brand>?>) {
                if (response.isSuccessful)
                {
                    val brand = response.body()


                }
            }

            override fun onFailure(call: Call<List<Brand>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

