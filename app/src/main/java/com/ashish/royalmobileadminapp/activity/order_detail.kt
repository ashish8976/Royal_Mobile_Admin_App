package com.ashish.royalmobileadminapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashish.royalmobileadminapp.databinding.ActivityOrderDetailBinding

class order_detail : AppCompatActivity() {
    lateinit var binding: ActivityOrderDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}