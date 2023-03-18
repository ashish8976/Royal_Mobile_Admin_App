package com.ashish.royalmobileadminapp.product

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.product.Accessories
import com.ashish.royalmobileadminapp.data.product.ColorObject
import com.ashish.royalmobileadminapp.data.product.ProductColor
import com.ashish.royalmobileadminapp.databinding.ActivityAddAccessroiesBinding
import com.ashish.royalmobileadminapp.utils.Constants
import com.ashish.royalmobileadminapp.utils.Constants.PICK_IMAGE_REQUEST
import com.ashish.royalmobileadminapp.utils.ImageUploading
import com.bumptech.glide.Glide
import retrofit2.Retrofit

class AddAccessroiesActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddAccessroiesBinding
    private lateinit var imageView: ImageView
    private lateinit var retrofit: Retrofit
    private lateinit var selectColor : ColorObject

    var imageUploading = ImageUploading(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddAccessroiesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val p = intent?.getStringExtra("pId")

        imageView = findViewById(R.id.accessImages)

        binding.accessImages.setOnClickListener {
            selectImage()
        }

        binding.addAccessButton.setOnClickListener {
            val i = Intent()
            binding.apply {
                if (!p.isNullOrEmpty())
                {
                    val a = Accessories(
                        access_id = 0,
                        product_id = p.toInt(),
                        specification = edtAccessSpecification.text.toString(),
                        price = edtAccessPrice.text.toString().toFloat()
                    )
                    i.putExtra("access",a)
                    i.putExtra("color",ProductColor(1,
                        colorSpinnerAccess.selectedItem.toString(),
                        imageUploading.imageUrl))
                    setResult(Activity.RESULT_OK,i)
                    finish()
                }
                else
                {
                    Toast.makeText(this@AddAccessroiesActivity, "PId is null", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if (isStoragePermissionGranted()) {
            ActivityCompat.requestPermissions(
                this,
                Constants.PERMISSION_STORAGE,
                Constants.REQUST_EXTERNAL_STORAGE
            )
        }

        val colors = listOf(
            "Red" to "#FF0000",
            "Green" to "#00FF00",
            "Blue" to "#0000FF",
        )

        val colorAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, colors.map { it.first })

        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.colorSpinnerAccess.adapter = colorAdapter

        binding.colorSpinnerAccess.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
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
                    Toast.makeText(
                        this@AddAccessroiesActivity,
                        "Nothing selected",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
    }

    private fun setSpinnerColor(color: String)
    {
        val drawble = GradientDrawable()
        drawble.setColor(Color.parseColor(color))
        drawble.shape = GradientDrawable.RECTANGLE
        drawble.setStroke(2, Color.BLACK)
        binding.colorSpinnerAccess.background = drawble
    }

    private fun isStoragePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data != null && data.data != null)
        {
            Glide.with(this).load(data.data).into(imageView)

            imageUploading.apply {
                imageUri = data.data
                sendProductImage(createImageMultipart())
            }
        }

    }
}

