package com.ashish.royalmobileadminapp.product

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.databinding.ActivityProductDetailsBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivityDetails : AppCompatActivity() {

    lateinit var binding : ActivityProductDetailsBinding
    lateinit var plist : List<Product>

    var product : Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        val p = intent.getSerializableExtra("product") as Product
        if (p != null){
            product = p
        }
        val sharedPreferences = getSharedPreferences(Constants.user_pref,Context.MODE_PRIVATE)

        setupData()
       // setupClickListener()

        setContentView(binding.root)
    }
    private fun setupData() {
        if (product != null){
            with(binding){
                productName.text = "Prodcut Name : "+ product!!.product_name
                productDesc.text = "Product Description : "+product!!.product_desc
                Glide.with(this@ProductActivityDetails)
                    .load(product?.productColor?.first()?.product_image?.let{
                            Constants.urlMaker(
                                it
                            )

                    }).into(binding.productImages)
                productVariant1.text = "Ram : " + product?.Mobile?.first()?.ram
                productVariant2.text = "Storage : "+product?.Mobile?.first()?.storage
                productVariant3.text = "Price : "+product?.Mobile?.first()?.price.toString()
//                if (product?.cate_id?.toString() == "Mobile"){
//                        productVariant1.text = product?.Mobile?.first()?.price.toString()
//                        productVariant2.text = product?.productColor?.first()?.color_name
//                    productVariant3.text = product?.Mobile?.first()?.ram
//                    productVariant4.text = product?.Mobile?.first()?.storage
//                }else{
//                        productVariant1.text = product?.productColor?.first()?.color_name
//                    productVariant2.text = product?.Accessories?.first()?.price.toString()
//                    productVariant3.text = product?.Accessories?.first()?.specification
//
//                }

            }
        }
    }

    fun GETPRODUCTBYCATEGORYID(id : Int){
        var ashish = Network_Service.networkInstance.getProductByCategoryId(id)
        ashish.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(
                call: Call<List<Product>?>,
                response: Response<List<Product>?>
            ) {
                if (response != null){
                    plist = response.body()!!

                }
                else
                {

                }

            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                Toast.makeText(this@ProductActivityDetails, "$t", Toast.LENGTH_SHORT).show()
            }
        })

    }
}























