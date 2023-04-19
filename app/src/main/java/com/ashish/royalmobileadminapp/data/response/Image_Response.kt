package com.ashish.royalmobileadminapp.data.response


import com.google.gson.annotations.SerializedName
data class Image_Response(

    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("url")
    val url: String
)


