package com.ashish.royalmobileadminapp


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ashish.royalmobileadminapp.activity.AddAdminActivity
import com.ashish.royalmobileadminapp.activity.MainActivity
import com.ashish.royalmobileadminapp.databinding.FragmentProfileBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants
import com.example.data.model.Admin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding
    var email : String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)


        isLoggedIn()
        getAdmin()
        binding.adminAccountImage.setOnClickListener {

        }

        binding.AddAdminButton.setOnClickListener {
             val intent = Intent(requireContext(),AddAdminActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun getAdmin()
    {
        val b = email?.let { Network_Service.networkInstance.getOneAdminDetails(it).enqueue(object : Callback<Admin?> {
            override fun onResponse(call: Call<Admin?>, response: Response<Admin?>) {
                binding.userEmail.text = response.body()?.admin_email
                binding.username.text = response.body()?.admin_name
            }

            override fun onFailure(call: Call<Admin?>, t: Throwable) {
                Toast.makeText(requireContext(),"Some Problem ${t.message}",Toast.LENGTH_LONG).show()
            }
        }) }
    }

    private fun isLoggedIn(): String? {
        val sharedPreferences = requireContext().getSharedPreferences(Constants.user_pref, Context.MODE_PRIVATE)
        email = sharedPreferences.getString(Constants.user_email,null)
        if(!email.isNullOrEmpty()){
            return email
        }

        return null
    }

}








