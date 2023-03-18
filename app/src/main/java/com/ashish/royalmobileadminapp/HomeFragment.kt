package com.ashish.royalmobileadminapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashish.royalmobileadminapp.databinding.FragmentHomeBinding
import com.ashish.royalmobileadminapp.product.*


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addProduct.setOnClickListener {
            val intent = Intent(requireContext(),AddProductActivity::class.java)
            startActivity(intent)
        }

        binding.addBrand.setOnClickListener{
            val intent = Intent(requireContext(),Add_Brand_Activity::class.java)
            startActivity(intent)
        }

        binding.allMobiles.setOnClickListener {
            val intent = Intent(requireContext(),AllMobileActivity::class.java)
            startActivity(intent)
        }

        binding.allAccessories.setOnClickListener {
            val  intent  = Intent(requireContext(),AllAccessoriesActivity::class.java)
            startActivity(intent)
        }

        binding.allProductDetails.setOnClickListener {
            val intent = Intent(requireContext(),AllProductActivity::class.java)
            startActivity(intent)
        }

    }

}