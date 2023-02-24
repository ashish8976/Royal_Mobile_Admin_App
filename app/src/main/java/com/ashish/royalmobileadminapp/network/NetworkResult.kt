package com.ashish.royalmobileadminapp.network


sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data:T,errorMessage: String? =null):NetworkResult<T>(data,errorMessage)
    class Error<T>(errorMessage: String,data: T? = null):NetworkResult<T>(data,errorMessage)
    class Loading<T>:NetworkResult<T>()

}