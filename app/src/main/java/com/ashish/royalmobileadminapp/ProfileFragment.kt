package com.ashish.royalmobileadminapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.ashish.royalmobileadminapp.activity.Logout_Activity
import com.ashish.royalmobileadminapp.utils.Constants.user_email
import com.ashish.royalmobileadminapp.databinding.FragmentProfileBinding
import com.ashish.royalmobileadminapp.utils.Constants


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

    lateinit var image : ImageView
    lateinit var btnChange : Button
    lateinit var btnSave : Button


    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        image.setImageURI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener {
            setupClickListeners()
        }

        binding.uploadImage.setOnClickListener {
            contract.launch("image/*")
        }

    }


    private fun setupClickListeners() {
        binding.btnLogout.setOnClickListener {
            val sharedPreferences= it.context.getSharedPreferences(Constants.user_pref, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(user_email)
            editor.apply()
            Toast.makeText(requireContext(),"Logged Out", Toast.LENGTH_LONG).show()
            val intent = Intent(requireContext(),Logout_Activity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

}