package com.ashish.royalmobileadminapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashish.royalmobileadminapp.*
import com.ashish.royalmobileadminapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.order_menu -> replaceFragment(OrderFragment())
                R.id.delivery_menu -> replaceFragment(DeliveryFragment())
                R.id.profile_menu -> replaceFragment(AdminProfileFragment())
                else ->{

                }
            }
            true

        }
    }

    private fun replaceFragment(fragmet : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.liner_frame,fragmet)
        fragmentTransaction.commit()
    }
}