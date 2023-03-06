package com.ashish.royalmobileadminapp.viewModel

import android.graphics.Bitmap
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import java.io.ByteArrayOutputStream
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ProfileViewModel : ViewModel()
{
    val profileImage = ObservableField<Bitmap>()

    fun uploadProfileImage()
    {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val byteArrayOutputStream = ByteArrayOutputStream()
                profileImage.get()?.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream)
                val imageBytes = byteArrayOutputStream.toByteArray()
                val file = File("storage/images/jpeg")
                val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val multipartBody = MultipartBody.Part.createFormData("image",file.name,requestFile)

                val response = Network_Service.networkInstance.uploadimage(multipartBody)

                withContext(Dispatchers.Main)
                {
                        Log.d(TAG, "Profile Image uploaded successfully :")
                }
            }
            catch (e :Exception)
            {
                    withContext(Dispatchers.IO)
                    {
                        Log.e(TAG,"Profile Image upload Error: ${e.message}")
                    }
            }
        }
    }

}

