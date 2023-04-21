package com.ashish.royalmobileadminapp.product

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.Category
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddCategoryBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants.category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCategoryActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addCategoryButton.setOnClickListener {
            //val id = binding.edtCategoryId.toString()
            val name = binding.edtCategoryName.toString()
           if (name.isEmpty()){
               Toast.makeText(this@AddCategoryActivity, "Category Added", Toast.LENGTH_SHORT).show()
           }else{
               addCategoryFunction()
           }
        }
    }

    private fun addCategoryFunction() {
      //  val cate_id = binding.edtCategoryId.text.toString().toInt()
        val cate_name = binding.edtCategoryName.text.toString()

        val data = Network_Service.networkInstance
        val sharedPreferences = getSharedPreferences(category,Context.MODE_PRIVATE)

       // val r = data.addCategory(Category(cate_name = cate_name))
        CoroutineScope(Dispatchers.IO).launch {
            data.addCategory(Category(cate_name = cate_name)).apply {
                if (this.success){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@AddCategoryActivity, "Category Added", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    var ash = this.message
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@AddCategoryActivity, ash, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}






























