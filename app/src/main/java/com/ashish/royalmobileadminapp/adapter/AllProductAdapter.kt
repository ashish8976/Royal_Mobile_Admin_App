package com.ashish.royalmobileadminapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.Product

class AllProductAdapter(val context: Context,val itemList : ArrayList<Product>):
    RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder>() {
    class AllProductViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var txtProductName : TextView = view.findViewById(R.id.txtProductName)
        var txtProductPrice : TextView = view.findViewById(R.id.txtProductPrice)
        var imgProductImage : ImageView = view.findViewById(R.id.imgProductImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_items,parent,false)
        return AllProductViewHolder(view)
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) {
       val product = itemList[position]

    }
}























