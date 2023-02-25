package com.ashish.royalmobileadminapp.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.databinding.ActivityAllProductBinding
import com.ashish.royalmobileadminapp.databinding.FragmentHomeBinding

class AllProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAllProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAllProductBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnAddProduct.setOnClickListener {
            val intent = Intent(this@AllProductActivity,AddProductActivity::class.java)
            startActivity(intent)
        }

    }
}