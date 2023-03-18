package com.ashish.royalmobileadminapp.network

import com.ashish.royalmobileadminapp.data.auth.Admin_Login_Request
import com.ashish.royalmobileadminapp.data.product.Accessories
import com.ashish.royalmobileadminapp.data.product.ProductColor
import com.ashish.royalmobileadminapp.data.product.Mobile
import com.ashish.royalmobileadminapp.data.product.Product
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.example.data.model.Brand
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface NetworkCall_Interface
{

    @POST("v1/admin/login")
    fun login(@Body loginDetails: Admin_Login_Request) : Call<Simple_Response>

    @POST("brand/add")
    fun addbrand(@Body brandDetails : Brand) : Call<Simple_Response>

    @Multipart
    @POST("profileImage/upload")
    fun uploadimage(@Part image: MultipartBody.Part): Call<Simple_Response>


    @POST("product/add")
    fun add_product(@Body productDetails: Product) : Call<Simple_Response>

    @POST("product/update")
    fun update_product(@Body productUpdateDetails: Product)  : Call<Simple_Response>


    @POST("product/delete")
    fun  delete_product(@Body productDeleteDetails: Product) : Call<Simple_Response>

    @POST("")
    fun add_mobile(@Body mobileDetails: Mobile) : Call<Simple_Response>

    @POST("")
    fun add_accessories(@Body accessoriesDetails: Accessories) : Call<Simple_Response>

    @POST("")
    fun add_color(@Body productColorDetails: ProductColor) : Call<Simple_Response>


    @GET("product/getAllProduct")
    fun getAllProduct(@Body getAllProductDetails: Product) : Call<Simple_Response>

    @GET("product/getAllMobile")
    fun getAllMobiles(@Body getAllMobilesDetails: Mobile) : Call<Simple_Response>


    @GET("product/getAllAccessories")
    fun getAllAccessories(@Body getAllAccessoriesDetails: Accessories) : Call<Simple_Response>

    @GET("brand/getAll")
    suspend fun getBrand() : List<Brand>
//    fun getAllBrandName(@Body getAllBrandDetails: Brand) : Call<Simple_Response>

}