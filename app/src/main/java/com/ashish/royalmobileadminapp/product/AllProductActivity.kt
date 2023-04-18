package com.ashish.royalmobileadminapp.product


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ashish.royalmobileadminapp.adapter.AllProductAdapter
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.databinding.ActivityAllProductBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.viewModel.ProductViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllProductBinding
    lateinit var vm : ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllProductBinding.inflate(layoutInflater)
        vm = ViewModelProvider(this)[ProductViewModel::class.java]
        setContentView(binding.root)
        loadProducts()

    }

    companion object{
        val db = Network_Service.networkInstance

    }

    private fun loadProducts() {
        val r = db.getAllProduct()
        r.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(
                call: Call<List<Product>?>,
                response: Response<List<Product>?>
            ) {
                if(response.body()!=null){
                    binding.rvProducts.adapter =
                        AllProductAdapter(this@AllProductActivity,response.body()!!,db,vm)
                //    binding.rvAllProducts.adapter = AllProductsAdapter(requireContext(),response.body()!!,db)
                }else{
                    Toast.makeText(this@AllProductActivity, "Response is null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                Toast.makeText(this@AllProductActivity, "Fail : $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}