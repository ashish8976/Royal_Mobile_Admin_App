package com.ashish.royalmobileadminapp.product

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.*
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddProductBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.viewModel.MobileViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    lateinit var vm : MobileViewModel
    var arr = mutableListOf<Brand>()
    var abc = mutableListOf<Category>()
    var mProduct = Product()
    var mMobile = mutableListOf<Mobile>()
    var mColor = mutableListOf<ProductColor>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //intent.getSerializableExtra("product") as Product
        Toast.makeText(this, "$mProduct", Toast.LENGTH_SHORT).show()
        vm = ViewModelProvider(this).get(MobileViewModel::class.java)
        getAllCategoryspinner()
        getAllBrandSpinner()
      //  vm = ViewModelProvider(this).get(AddProductActivityViewModel::class.java)

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

        vm.product.observe(this){
            Toast.makeText(this@AddProductActivity, "VM C : $it", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setupProduct() {
        mProduct.apply {
            binding.apply {
                if(!edtProductId.text.toString().isNullOrEmpty()) {
                    product_id = edtProductId.text.toString().toInt()
                    Toast.makeText(this@AddProductActivity, "$mProduct", Toast.LENGTH_SHORT).show()
                }
                product_name = edtproductName.text.toString()
                product_desc = edtProductDesc.text.toString()
                val CategoryName = abc.filter {
                    it.cate_name==edtproductCategory.selectedItem.toString()
                }
                if (CategoryName.isNotEmpty()) {
                    cate_id = CategoryName.first().cate_id
                }
//                cate_name = edtproductCategory.selectedItem.toString()
                val BrandName = arr.filter {
                    it.brand_name==edtproductBrandname.selectedItem.toString()
                }
                if (BrandName.isNotEmpty()) {
                   // brand_id = BrandName.first().brand_id
                }
            }
        }


        vm.product.postValue(mProduct)
        vm.msg.postValue(mProduct.toString())
        //Toast.makeText(this@AddProductActivity, "VM : ${vm.product.value}", Toast.LENGTH_LONG).show()

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                getAllCategoryspinner()
                getAllBrandSpinner()
                val m = data?.getSerializableExtra("mobile") as Mobile
                //mProduct.Mobile = mutableListOf(data?.getSerializableExtra("mobile") as Mobile)
                val mlist = mProduct.Mobile
                mMobile.add(m)
                val c = data.getSerializableExtra("colors") as ProductColor
                //mProduct.productColor = mutableListOf(data.getSerializableExtra("colors") as ProductColor)
                mColor.add(c)
                mProduct.productColor = mColor
                mProduct.Mobile = mMobile
                Toast.makeText(this, "${mProduct.productColor?.size}", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(
                    this@AddProductActivity,
                    "Please Enter Data",
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
                    binding.errorText.text = it.message.toString()
                   // Toast.makeText(this@AddProductActivity,it.message, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                Toast.makeText(this@AddProductActivity, "Some Problem occur", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun getAllCategoryspinner() {
            val t = Network_Service.networkInstance.getCategory()
        t.enqueue(object : Callback<List<Category>?> {
            override fun onResponse(
                call: Call<List<Category>?>,
                response: Response<List<Category>?>
            ) {
                abc = response.body()!!.toMutableList()
                val rachna = abc.map {
                    it.cate_name
                }
                val adp = ArrayAdapter(this@AddProductActivity,R.layout.simple_spinner_brand_name,rachna)
                binding.edtproductCategory.adapter = adp
                //setupProduct()
            }

            override fun onFailure(call: Call<List<Category>?>, t: Throwable) {
                Toast.makeText(this@AddProductActivity,"Some Problem occur",Toast.LENGTH_LONG).show()
            }
        })
    }

   private fun getAllBrandSpinner()
   {
       val a = Network_Service.networkInstance.getBrand()
       a.enqueue(object : Callback<List<Brand>?> {
           override fun onResponse(call: Call<List<Brand>?>, response: Response<List<Brand>?>) {

               arr = response.body()!!.toMutableList()
               val are = arr.map {
                   it.brand_name
               }
               val adp = ArrayAdapter(this@AddProductActivity,R.layout.simple_spinner_brand_name,are)
               binding.edtproductBrandname.adapter = adp
               //setupProduct()
           }

           override fun onFailure(call: Call<List<Brand>?>, t: Throwable) {
               Toast.makeText(this@AddProductActivity, "Some Problem occur", Toast.LENGTH_SHORT).show()
           }
       })
   }

}

