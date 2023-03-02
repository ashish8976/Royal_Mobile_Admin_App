package com.ashish.royalmobileadminapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ashish.royalmobileadminapp.URL
import com.surajmanshal.mannsignadmin.network.NetworkCallsInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network_Service
{
    val networkInstance : NetworkCall_Interface
    init {
        val retrofit = Retrofit.Builder().baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        networkInstance = retrofit.create(NetworkCall_Interface::class.java)
    }
    fun checkForInternet(context : Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo?=connectivityManager.activeNetworkInfo
        return if(activeNetwork?.isConnected!=null){
            activeNetwork.isConnected
        }else{
            false
        }
    }
}