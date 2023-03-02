package com.ashish.royalmobileadminapp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.FragmentProfileBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants.REQUEST_CODE_IMAGE
import com.surajmanshal.mannsignadmin.network.NetworkService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ProfileFragment : Fragment() {


    private var imageUri: Uri? = null
    lateinit var binding : FragmentProfileBinding

    val file = File("storage/images/image.jpg")
    val requestfile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file)
    val image = MultipartBody.Part.createFormData("image",file.name,requestfile)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view)

        binding.adminAccountImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,1)
        }
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun openImage() {
        Intent(Intent.ACTION_PICK).also {
            it.type = "image/*"
            val mimetype = arrayOf("image/jpeg","image/png")
            it.putExtra(Intent.EXTRA_MIME_TYPES,mimetype)
            startActivityForResult(it,REQUEST_CODE_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            imageUri = data.data!!
            binding.adminAccountImage.setImageURI(imageUri)
        }
    }


    private fun uploadImages()
    {
        Network_Service.networkInstance.uploadimage(image).enqueue(object : Callback<Simple_Response?> {
            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {

            }

            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        //    api.uploadImage(image).enqueue(object : Callback<UploadResponse> {
//        override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
//            // handle success
//        }
//
//        override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
//            // handle failure
//        }
//    })
    }




}