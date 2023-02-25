package com.surajmanshal.mannsignadmin.network

import android.telecom.Call.Details
import com.ashish.royalmobileadminapp.data.auth.Admin_Login_Request
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.example.data.model.Brand
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface NetworkCallsInterface {


    @POST("v1/admin/login")
    fun login(@Body loginDetails: Admin_Login_Request) : Call<Simple_Response>

    @POST("brand/add")
    fun addbrand(@Body brandDetails : Brand) : Call<Simple_Response>

}
