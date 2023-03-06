package com.ashish.royalmobileadminapp

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableField
import com.ashish.royalmobileadminapp.data.response.Image_Response
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.FragmentProfileBinding
import com.ashish.royalmobileadminapp.utils.Constants.REQUEST_CODE_IMAGE
import com.ashish.royalmobileadminapp.viewModel.ProfileViewModel
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.File


class ProfileFragment : Fragment() {


    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var context: Context
    private var imageUri: Uri? = null

    val file = File("storage/images/image.jpg")
    val requestfile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
    val image = MultipartBody.Part.createFormData("image", file.name, requestfile)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.adminAccountImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE_IMAGE)
        }

        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            imageUri = data.data
            binding.adminAccountImage.setImageURI(imageUri)

            uploadImage(file)
        }
    }

    private fun uploadImage(file: File) {
        val fileRequestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("image",file.name,fileRequestBody)

        val call = Network_Service.networkInstance.uploadimage(filePart)

        call.enqueue(object : Callback<Image_Response?> {
            override fun onResponse(
                call: Call<Image_Response?>,
                response: Response<Image_Response?>
            ) {
                val imageResponse = response.body()
                Toast.makeText(requireContext(),"Image uploaded successfully $imageResponse",
                    Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Image_Response?>, t: Throwable) {
                Toast.makeText(requireContext(),"Image Not  uploaded", Toast.LENGTH_LONG).show()
            }
        })
    }






}

//    private fun uploadProfileImage() {
//        val profileImage = ObservableField<Bitmap>()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val byteArrayOutputStream = ByteArrayOutputStream()
//                profileImage.get()?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//                val imageBytes = byteArrayOutputStream.toByteArray()
//                val file = File("storage/images/jpeg")
//                val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//                val multipartBody =
//                    MultipartBody.Part.createFormData("image", file.name, requestFile)
//
//                val response = Network_Service.networkInstance.uploadimage(multipartBody)
//
//                withContext(Dispatchers.Main)
//                {
//                    Log.d(Constants.TAG, "Profile Image uploaded successfully :")
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.IO)
//                {
//                    Log.e(Constants.TAG, "Profile Image upload Error: ${e.message}")
//                }
//            }
//        }
//    }


//private fun getRealPathFromUri(context: Context, uri: Uri): String? {
//    var filePath: String? = null
//    val wholeID = DocumentsContract.getDocumentId(uri)
//
//    val id = wholeID.split(":")[1]
//    val column = arrayOf(MediaStore.Images.Media.DATA)
//
//    val sel = MediaStore.Images.Media._ID + "=?"
//    val cursor = context?.contentResolver?.query(
//        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//        column, sel, arrayOf(id), null
//    )
//
//    if (cursor != null) {
//        val columnIndex = cursor.getColumnIndex(column[0])
//        if (cursor.moveToFirst()) {
//            filePath = cursor.getString(columnIndex)
//        }
//        cursor.close()
//    }
//
//    return filePath
//}



