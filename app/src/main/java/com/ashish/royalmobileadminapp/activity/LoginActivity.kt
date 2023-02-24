package com.ashish.royalmobileadminapp.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashish.royalmobileadminapp.Constants.user_email
import com.ashish.royalmobileadminapp.Constants.user_pref
import com.ashish.royalmobileadminapp.R
import com.ashish.royalmobileadminapp.data.model.Admin_Login_Request
import com.ashish.royalmobileadminapp.data.response.Simple_Response
import com.ashish.royalmobileadminapp.databinding.ActivityLoginBinding
import com.surajmanshal.mannsignadmin.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    var email : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoggedIn()


        binding.btnLogin.setOnClickListener {
            validateUser()
        }

    }

    private fun isLoggedIn() {
        val sharedPreferences= getSharedPreferences(user_pref, Context.MODE_PRIVATE)
        email = sharedPreferences.getString(user_email,null)
        if(!email.isNullOrEmpty()){
            startActivity(Intent(this,MainActivity::class.java))
            Toast.makeText(this@LoginActivity,"Navigate to homepage",Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun validateUser() {

        val email = binding.edtEmail.text.toString()
        val pass = binding.edtPassword.text.toString()
        val db = NetworkService.networkInstance
        val sharedPreferences= getSharedPreferences(user_pref, Context.MODE_PRIVATE)

        val r = db.login(Admin_Login_Request(adminEmail = email, adminPassword = pass))

        r.enqueue(object : Callback<Simple_Response?> {

            override fun onResponse(
                call: Call<Simple_Response?>,
                response: Response<Simple_Response?>
            ) {
                val b = response.body()

                if (b != null) {
                    if(b.success){
                        val s = sharedPreferences.edit()
                        s.putString(user_email,email)
                        s.apply()
                        Toast.makeText(this@LoginActivity, "Login success", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@LoginActivity, "Wrong password", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "response is null", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Simple_Response?>, t: Throwable) {
                binding.loginTitle.text = t.message.toString()
                Toast.makeText(this@LoginActivity, t.message.toString(), Toast.LENGTH_LONG).show()
            }
        })


    }
}