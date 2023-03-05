package com.ashish.royalmobileadminapp

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashish.royalmobileadminapp.databinding.FragmentProfileBinding
import com.ashish.royalmobileadminapp.utils.Constants.REQUEST_CODE_IMAGE
import com.ashish.royalmobileadminapp.viewModel.ProfileViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class ProfileFragment : Fragment() {


   private lateinit var viewModel : ProfileViewModel
    private lateinit var binding : FragmentProfileBinding
   private var imageUri : Uri? = null

    val file = File("storage/images/image.jpg")
    val requestfile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
    val image = MultipartBody.Part.createFormData("image",file.name,requestfile)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding = FragmentProfileBinding.inflate(inflater,container,false)


        binding.adminAccountImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,REQUEST_CODE_IMAGE)
        }
        binding.uploadImage.setOnClickListener {

        }
        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            imageUri = data.data
            binding.adminAccountImage.setImageURI(imageUri)
        }
    }

    private fun uploadImage()
    {

    }



}