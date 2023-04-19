package com.ashish.royalmobileadminapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ashish.royalmobileadminapp.data.product.Order
import com.ashish.royalmobileadminapp.databinding.OrderItemsBinding


class AllOrderAdapter(
    val context: Context,
    private val itemList : List<Order>,
//    val db : NetworkCall_Interface,
//    val vm : OrderViewModel
    ):RecyclerView.Adapter<AllOrderAdapter.AllOrderViewHolder>()
{
        class  AllOrderViewHolder(
            val binding : OrderItemsBinding
        ):ViewHolder(binding.root){
            val orderID = binding.txtOrderId
            val orderDate = binding.txtOrderDate
            val orderTotalPrice = binding.txtOrderTotal
            val orderEmail = binding.txtOrderEmailId
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
       return AllOrderViewHolder(
           OrderItemsBinding.inflate(
               LayoutInflater.from(
                   context
               ),parent,false
           )
       )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {
        val rachna = itemList[position]
        with(holder){
            orderID.text = rachna.order_id
            orderDate.text = "Order date : "+rachna.order_date.toString()
            orderEmail.text = rachna.Email
            orderTotalPrice.text = rachna.total.toString()
        }
    }
}




































