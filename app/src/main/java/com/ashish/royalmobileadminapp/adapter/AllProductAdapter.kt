package com.ashish.royalmobileadminapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ashish.royalmobileadminapp.data.product.Category
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.databinding.HorizontalScrollItemLayoutBinding
import com.ashish.royalmobileadminapp.network.NetworkCall_Interface
import com.ashish.royalmobileadminapp.product.ProductActivityDetails
import com.ashish.royalmobileadminapp.utils.Constants
import com.ashish.royalmobileadminapp.viewModel.ProductViewModel
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllProductAdapter(
    val context: Context,val itemList : List<Product>,
    val db : NetworkCall_Interface, val vm : ProductViewModel):
    RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder>() {
    class AllProductViewHolder(val binding: HorizontalScrollItemLayoutBinding):
    ViewHolder(binding.root){
        val imageProductImage = binding.imgProductImage
        val productName = binding.txtProductName
        val productCategory = binding.txtProductCategory
        val productPrice = binding.txtProductPrice
        val productCard =binding.productCard
        val deleteProductBtn = binding.btnDeleteProduct
        // val btnRemoveCart = binding.btnRemoveCartItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder {
        return AllProductViewHolder(
            HorizontalScrollItemLayoutBinding.inflate(
                LayoutInflater.from(context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) {
       val tanu = itemList[position]
        with(holder){
            Glide.with(context).load(
                Constants.urlMaker(tanu.productColor?.first()?.product_image!!)
            ).into(imageProductImage)

            productName.text = tanu.product_name
            productPrice.text =tanu.Mobile?.first()?.price.toString()

            getCatById(tanu.cate_id!!){
                productCategory.text = it
            }

            imageProductImage.setOnClickListener {
              //  Toast.makeText(context, "Product Clicked", Toast.LENGTH_SHORT).show()
                val ch = Intent(
                    context,ProductActivityDetails::class.java
                )
              //  ch.putExtra("product",tanu)
                ch.putExtra("product",tanu)
                context.startActivity(ch)
            }

            productCard.setOnClickListener {
                val ch = Intent(
                    context,ProductActivityDetails::class.java
                )
                ch.putExtra("product",tanu)
                context.startActivity(ch)
            }

//            deleteProductBtn.setOnClickListener {
//                Toast.makeText(context, "button Click", Toast.LENGTH_SHORT).show()
//            }

            deleteProductBtn.setOnClickListener {
                val ak = AlertDialog.Builder(context)
                ak.setTitle("Remove Product ? ")
                ak.setMessage("After Delete Product you can added it later ..")
                ak.setPositiveButton("Yes"){d,w->
                    if (tanu.product_id != null) {
                        vm.deleteProduct(tanu.product_id.toString().toInt())
                       // Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(context, "Product Not Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
                ak.setNegativeButton("No"){d,w->
                    d.dismiss()
                }
                ak.show()
            }
        }
    }

    fun getCatById(id : Int,s : (String)-> Unit){
        var d = db.getCategoryById(id)
        d.enqueue(object : Callback<Category?> {
            override fun onResponse(call: Call<Category?>, response: Response<Category?>) {
                if(response.body()!=null){
                    s(response.body()!!.cate_name)
                }
            }

            override fun onFailure(call: Call<Category?>, t: Throwable) {
                Toast.makeText(context, "$t", Toast.LENGTH_SHORT).show()
            }
        })
    }




}






















//        var ashish = db.getProductByCategoryId(id)
//        ashish.enqueue(object : Callback<Product?> {
//            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
//                if (response.body() != null){
//                    s(response.body()!!.cate_id.toString())
//                }
//            }
//            override fun onFailure(call: Call<Product?>, t: Throwable) {
//                Toast.makeText(context, "$t", Toast.LENGTH_LONG).show()
//            }
//        })

















