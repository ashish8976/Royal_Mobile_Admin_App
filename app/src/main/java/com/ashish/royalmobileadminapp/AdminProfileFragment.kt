package com.ashish.royalmobileadminapp

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashish.royalmobileadminapp.databinding.FragmentAdminProfileBinding
import com.ashish.royalmobileadminapp.utils.Constants.REQUEST_CODE_IMAGE
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import java.io.File
import okhttp3.RequestBody


class AdminProfileFragment : Fragment() {

    private lateinit var binding: FragmentAdminProfileBinding

    private lateinit var context: Context

    private var imageUri : Uri? = null

    val file = File("storage/image.jpg")
    val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(),file)
    val image = MultipartBody.Part.createFormData("image",file.name,requestFile)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_admin_profile,container,false)
        binding = FragmentAdminProfileBinding.bind(view)


        binding.adminAccountImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,REQUEST_CODE_IMAGE)
        }

        binding.uploadImage.setOnClickListener {

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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