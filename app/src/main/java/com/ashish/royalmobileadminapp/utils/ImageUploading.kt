package com.ashish.royalmobileadminapp.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ashish.royalmobileadminapp.URL
import com.ashish.royalmobileadminapp.data.response.Image_Response
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.network.Network_Service
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class ImageUploading(private val activity : Activity) {

    var imageUri : Uri? = null
    var imageUrl : String? = null


    fun createImageMultipart(): MultipartBody.Part {
        return with(activity){
            val dir = applicationContext.filesDir
            val file = File(dir, "image.png")

            val outputStream = FileOutputStream(file)
            contentResolver.openInputStream(imageUri!!)?.copyTo(outputStream)

            val requestBody = RequestBody.create("image/jpg".toMediaTypeOrNull(),file)
            val part = MultipartBody.Part.createFormData("product",file.name,requestBody)
            println("${part.body.contentType()}" +"}")
            return@with part
        }
    }

     fun sendProductImage(part: MultipartBody.Part){
        try {
//            val response = uploadProductImage(part,languageId)
            val call = Network_Service.networkInstance.uploadimage(part)

            call.enqueue(object : Callback<Simple_Response?> {
                override fun onResponse(
                    call: Call<Simple_Response?>,
                    response: Response<Simple_Response?>
                ) {
                     response.body()?.let { 
                         if(it.success){
                             Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                             imageUrl = it.message
                         }

                     }
              
                }

                override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                    // todo :
                    Toast.makeText(activity, "$t", Toast.LENGTH_SHORT).show()
                    println("$t")
                }
            })
        }catch (e : Exception){
            //println("$e ${Response.value?.message}")
        }
    }

//     fun sendProfileImage(part: MultipartBody.Part){
//        try {
//     //       val response = repository.uploadProfileImage(part)
//       //     _serverResponse.postValue(response)
//         //   _imageUploadResponse.postValue(response)
//        }catch (e : Exception){
//           // println("$e ${serverResponse.value?.message}")
//        }
//    }



}