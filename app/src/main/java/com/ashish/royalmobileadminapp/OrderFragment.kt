package com.ashish.royalmobileadminapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ashish.royalmobileadminapp.adapter.AllOrderAdapter
import com.ashish.royalmobileadminapp.data.product.Order
import com.ashish.royalmobileadminapp.databinding.FragmentOrderBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderFragment : Fragment() {
        lateinit var binding: FragmentOrderBinding
      //  lateinit var vm : OrderViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater,container,false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadOrder()
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{
        val db = Network_Service.networkInstance
    }
    private fun loadOrder(){
        val o = db.getAllOrder()
        o.enqueue(object : Callback<List<Order>?> {
            override fun onResponse(
                call: Call<List<Order>?>,
                response: Response<List<Order>?>
            ) {
                if (response.body() != null){
                    binding.rvOrder.adapter =
                        AllOrderAdapter(requireContext(),response.body()!!)
                  //  Toast.makeText(requireContext(), "${response.body()}", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(requireContext(), "Response is null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Order>?>, t: Throwable) {
                Toast.makeText(requireContext(), "Fail $t", Toast.LENGTH_SHORT).show()
            }
        })
    }


}