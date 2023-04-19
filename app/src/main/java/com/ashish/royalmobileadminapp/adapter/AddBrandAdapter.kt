package com.ashish.royalmobileadminapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ashish.royalmobileadminapp.data.product.Brand
import com.ashish.royalmobileadminapp.databinding.AddBrandLayoutBinding

class AddBrandAdapter(val c:Context, val i:List<Brand>
):RecyclerView.Adapter<AddBrandAdapter.AllBrandAdapter>()
{
    class AllBrandAdapter(
        val binding : AddBrandLayoutBinding
    ):ViewHolder(binding.root){
        val brandName = binding.brandName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllBrandAdapter {
        return AllBrandAdapter(
            AddBrandLayoutBinding.inflate(
                LayoutInflater.from(c),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
       return i.size
    }

    override fun onBindViewHolder(holder: AllBrandAdapter, position: Int) {
        val block = i[position]
        with(holder){
            brandName.text = block.brand_name
        }
    }


}