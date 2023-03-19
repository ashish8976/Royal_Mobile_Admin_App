package com.ashish.royalmobileadminapp.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.auth.Admin_Register_Request
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityAddAdminBinding
import com.ashish.royalmobileadminapp.network.Network_Service
import com.ashish.royalmobileadminapp.utils.Constants.user_pref
import com.ashish.royalmobileadminapp.utils.Constants.user_register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAdminActivity : AppCompatActivity() {

   lateinit var binding : ActivityAddAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddAdminBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        binding.btnAdminAdd.setOnClickListener {
            AddAdminFunction()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex(pattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex(pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=_!])(?=\\S+$).{8,}$")
        return passwordRegex.matches(password)
    }

    private fun AddAdminFunction()
    {
            val name = binding.edtName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        val database = Network_Service.networkInstance

        val sharedPreferences = getSharedPreferences(user_pref,Context.MODE_PRIVATE)

        val add = database.register(Admin_Register_Request(
            adminName = name, adminEmail = email , adminPassword = password))

        add.enqueue(object : Callback<Simple_Response?> {
            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {
                val b = response.body()

                if (b != null){
                    if (b.success) {
                        val s = sharedPreferences.edit()
                        s.putString(user_register,email)
                        s.apply()
                        Toast.makeText(this@AddAdminActivity, "Admin Added successfully", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Toast.makeText(this@AddAdminActivity,"Some Problem occur",Toast.LENGTH_LONG).show()
                    }
                }
                else
                {
                    Toast.makeText(this@AddAdminActivity, "Response is  null", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
             //   binding.adminAddTitle.text = t.message.toString()
                Toast.makeText(this@AddAdminActivity,t.message.toString(),Toast.LENGTH_LONG).show()
            }
        })

    }
}