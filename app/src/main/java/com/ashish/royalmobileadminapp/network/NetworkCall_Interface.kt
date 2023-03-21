package com.ashish.royalmobileadminapp.network

import android.telecom.Call.Details
import com.ashish.royalmobileadminapp.data.auth.Admin_Login_Request
import com.ashish.royalmobileadminapp.data.auth.Admin_Register_Request
import com.ashish.royalmobileadminapp.data.product.*
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkCall_Interface
{

    @POST("v1/admin/login")
    fun login(@Body loginDetails: Admin_Login_Request) : Call<Simple_Response>

    @POST("v1/admin/register")
    fun register(@Body registerDetails: Admin_Register_Request) : Call<Simple_Response>

    @POST("brand/add")
    fun addbrand(@Body brandDetails : Brand) : Call<Simple_Response>

    @POST("category/add")
    fun addCategory(@Body categoryDetails: Category) : Call<Simple_Response>

    @Multipart
    @POST("profileImage/upload")
    fun uploadimage(@Part image: MultipartBody.Part): Call<Simple_Response>


    @POST("product/add")
    fun add_product(@Body productDetails: Product) : Call<Simple_Response>

    @POST("product/update")
    fun update_product(@Body productUpdateDetails: Product)  : Call<Simple_Response>


    @POST("product/delete")
    fun  delete_product(@Body productDeleteDetails: Product) : Call<Simple_Response>



    @GET("product/getAllProduct")
    fun getAllProduct() : Call<List<Product>>

    @GET("product/getAllMobile")
    fun getAllMobiles() : Call<List<Mobile>>


    @GET("product/getAllAccessories")
    fun getAllAccessories() : Call<List<Accessories>>

    @GET("brand/getAll")
    fun getBrand() : Call<List<Brand>>

    @GET("category/getAll")
    fun getCategory() : Call<List<Category>>

}