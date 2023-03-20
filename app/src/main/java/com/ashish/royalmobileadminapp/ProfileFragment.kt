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
import com.ashish.royalmobileadminapp.activity.AddAdminActivity
import com.ashish.royalmobileadminapp.activity.ForgetPasswordActivity
import com.ashish.royalmobileadminapp.activity.LoginActivity
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

        binding.AddAdminButton.setOnClickListener {
             val intent = Intent(requireContext(),AddAdminActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(requireContext(),ForgetPasswordActivity::class.java)
            startActivity(intent)
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
        val filePart = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)

        val call = Network_Service.networkInstance.uploadimage(filePart)


    }
}








