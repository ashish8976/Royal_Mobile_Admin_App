package com.ashish.royalmobileadminapp.product

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.ColorObject
import com.ashish.royalmobileadminapp.data.response.Image_Response
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddMobileBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants.PERMISSION_STORAGE
import com.ashish.royalmobileadminapp.utils.Constants.PICK_IMAGE_REQUEST
import com.ashish.royalmobileadminapp.utils.Constants.REQUEST_CODE_IMAGE
import com.ashish.royalmobileadminapp.utils.Constants.REQUST_EXTERNAL_STORAGE
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

class AddMobileActivity : AppCompatActivity() {


    lateinit var binding : ActivityAddMobileBinding
     private lateinit var imageView: ImageView
    private lateinit var retrofit: Retrofit
    lateinit var selectColor : ColorObject


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMobileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = findViewById(R.id.mobileImages)

        binding.mobileImages.setOnClickListener {
            selectImage()
        }

        if (isStoragePermissionGranted())
        {
            ActivityCompat.requestPermissions(this, PERMISSION_STORAGE, REQUST_EXTERNAL_STORAGE)
        }


        val colors = listOf(
            "Red" to "#FF0000",
            "Green" to "#00FF00",
            "Blue" to "#0000FF",
        )

        val colorAdapter = ArrayAdapter(
            this,android.R.layout.simple_spinner_dropdown_item,colors.map { it.first })

        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinner.adapter = colorAdapter

        binding.colorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedColor = colors[position].second
                setSpinnerColor(selectedColor)
//                Toast.makeText(this@AddMobileActivity,"Selected color : $selectedColor",Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@AddMobileActivity,"Nothing selected",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setSpinnerColor(color : String)
    {
        val drawble = GradientDrawable()
        drawble.setColor(Color.parseColor(color))
        drawble.shape = GradientDrawable.RECTANGLE
        drawble.setStroke(2,Color.BLACK)
        binding.colorSpinner.background = drawble
    }

    private fun isStoragePermissionGranted() : Boolean
    {
        return ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    private fun selectImage() {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null)
        {
            val imageUri = data.data
            val filepath = getRealPathFromURI(imageUri)
            val file = File(filepath)

            Glide.with(this).load(file).into(imageView)

//            Glide.with(this).load(file).into(imageView)

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
                Toast.makeText(this@AddMobileActivity,"Image uploaded successfully $imageResponse",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Image_Response?>, t: Throwable) {
                Toast.makeText(this@AddMobileActivity,"Image Not  uploaded",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getRealPathFromURI(uri: Uri?): String {
            val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri!!,projection,null,null,null)
        val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val filepath = cursor.getString(columnIndex)
        cursor.close()

        return filepath
    }
}

