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
            val id = binding.edtCategoryId.toString()
            val name = binding.edtCategoryName.toString()
            if (id.isEmpty() && name.isEmpty())
            {
                Toast.makeText(this, "some field missing", Toast.LENGTH_SHORT).show()
            }
            else
            {
                addCategoryFunction()
            }
        }
    }

    private fun addCategoryFunction() {
        val cate_id = binding.edtCategoryId.text.toString().toInt()
        val cate_name = binding.edtCategoryName.text.toString()

        val data = Network_Service.networkInstance
        val sharedPreferences = getSharedPreferences(category,Context.MODE_PRIVATE)

        val r = data.addCategory(
            Category(
                cate_id = cate_id,
                cate_name = cate_name
            )
        )

        r.enqueue(object : Callback<Simple_Response?> {
            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {
                 val b = response.body()
                if (b != null)
                {
                    if (b.success)
                    {
                        val s = sharedPreferences.edit()
                        s.putString(category,cate_id.toString())
                        s.apply()
                        Toast.makeText(this@AddCategoryActivity, "Category Added Successfully", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this@AddCategoryActivity, "Brand not Added", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this@AddCategoryActivity, "Some Problem occur", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                Toast.makeText(this@AddCategoryActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}






























