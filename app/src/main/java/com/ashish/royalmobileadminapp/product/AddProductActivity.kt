package com.ashish.royalmobileadminapp.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashish.royalmobileadminapp.databinding.ActivityAddProductBinding

class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addMobiles.setOnClickListener {
            val intent = Intent(this,AddMobileActivity::class.java)
            startActivity(intent)
        }

        binding.addAccessories.setOnClickListener {
            val intent = Intent(this,AddAccessroiesActivity::class.java)
            startActivity(intent)
        }

        binding.addProductButton.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {

    }
}