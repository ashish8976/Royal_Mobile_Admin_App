package com.ashish.royalmobileadminapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.network.Network_Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel()
{
    val msg = MutableLiveData("")
    val products = MutableLiveData<List<Product>?>()
//    val carts = MutableLiveData<List<Cart>>()

    companion object{
        val db = Network_Service.networkInstance
    }

    fun deleteProduct(pid : Int){
        val sumit  = db.deleteProduct(pid)
        sumit.enqueue(object : Callback<Simple_Response?> {
            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {
                if (response.body()!=null ){
                        msg.postValue("Product Deleted")
                    getAllProduct()
                }else
                {
                    msg.postValue("Response is null")
                }
            }
            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                msg.postValue("$t")
            }
        })
    }

    private fun getAllProduct() {
        val kunal = db.getAllProduct()
        kunal.enqueue(object : Callback<List<Product>?> {
            override fun onResponse(
                call: Call<List<Product>?>,
                response: Response<List<Product>?>
            ) {
                if (response.body()!=null) {
                    val id = response.body()?.sortedBy {
                        it.product_id
                    }
                    products.postValue(id)
                }
                else {
                    msg.postValue("Response is null")
                }
            }

            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                msg.postValue("Some Problem $t")
            }
        })
    }
}

