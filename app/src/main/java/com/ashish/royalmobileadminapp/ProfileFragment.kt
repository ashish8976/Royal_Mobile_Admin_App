package com.ashish.royalmobileadminapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ashish.royalmobileadminapp.utils.Constants.user_email
import com.ashish.royalmobileadminapp.databinding.FragmentProfileBinding
import com.ashish.royalmobileadminapp.utils.Constants


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view)


        setupClickListeners()
        return binding.root
    }

    private fun setupClickListeners() {
        binding.btnLogout.setOnClickListener {
            val sharedPreferences= it.context.getSharedPreferences(Constants.user_pref, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove(user_email)
            editor.apply()
            Toast.makeText(requireContext(),"Logged Out", Toast.LENGTH_LONG).show()
            requireActivity().finish()
        }
    }

}