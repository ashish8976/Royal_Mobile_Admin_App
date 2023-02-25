package com.surajmanshal.mannsignadmin.network

import android.telecom.Call.Details
import com.ashish.royalmobileadminapp.data.model.Admin_Login_Request
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface NetworkCallsInterface {


    @POST("v1/admin/login")
    fun login(@Body loginDetails: Admin_Login_Request) : Call<Simple_Response>



}
