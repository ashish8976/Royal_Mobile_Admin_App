package com.ashish.royalmobileadminapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ashish.royalmobileadminapp.activity.CustomerDetailsActivity
import com.ashish.royalmobileadminapp.databinding.CutomerListLayoutBinding
import com.ashish.royalmobileadminapp.product.ProductActivityDetails
import com.example.data.model.Customer

class AllCustomerAdapter(
    val context: Context, private val itemList : List<Customer>
    ):RecyclerView.Adapter<AllCustomerAdapter.AllCustomerViewHolder>()
{
    class AllCustomerViewHolder(
        val binding : CutomerListLayoutBinding
    ):ViewHolder(binding.root){
        val customerName = binding.txtCustomerName
        val customerEmail = binding.txtCustomerEmailId
      //  val customerPhone = binding.txtCustomerPhoneno
        val customerAddress = binding.txtCustomerAddress
        val customerCard = binding.customerCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCustomerViewHolder {
        return AllCustomerViewHolder(
            CutomerListLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: AllCustomerViewHolder, position: Int) {
        val ssc = itemList[position]
        with(holder){
            customerEmail.text = "Customer Email : "+ssc.email
            customerAddress.text = "Customer Address : "+ssc.cust_Address
            customerName.text = "Customer Name : "+ssc.first_name

            customerCard.setOnClickListener {
                val ch = Intent(
                    context,CustomerDetailsActivity::class.java
                )
                ch.putExtra("customer",ssc)
                context.startActivity(ch)
            }

        }
    }
}

