package com.ashish.royalmobileadminapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.royalmobileadminapp.data.product.Order
import com.ashish.royalmobileadminapp.network.Network_Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : ViewModel() {
    val msg = MutableLiveData("")
    val orders = MutableLiveData<List<Order>?>()
    companion object{
        val db = Network_Service.networkInstance
    }

    private fun getAllOrder(){
        val ashish = db.getAllOrder()
        ashish.enqueue(object : Callback<List<Order>?> {
            override fun onResponse(
                call: Call<List<Order>?>,
                response: Response<List<Order>?>
            ){
                if (response != null){
                    val id = response.body()?.sortedBy {
                        it.order_id
                    }
                    orders.postValue(id)
                }
                else{
                    msg.postValue("Response is null")
                }
            }
            override fun onFailure(call: Call<List<Order>?>, t: Throwable) {
                msg.postValue("Some Problem occur $t")
            }
        })
    }

}