package com.example.petme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.petme.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : AppCompatActivity() {
    private lateinit var bindingRegister : ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegister = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(bindingRegister.root)

        bindingRegister.signupBtn.setOnClickListener{
            addRegister()
        }
    }

    private fun addRegister(){
        val api: UserAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
        api.registerUser(
            bindingRegister.signupUsername.text.toString(),
            bindingRegister.signupEmail.text.toString(),
            bindingRegister.signupTel.text.toString(),
            bindingRegister.signupAddress.text.toString(),
            bindingRegister.signupPassword.text.toString()
        ).enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: retrofit2.Response<User>
            ) {
                if (response.isSuccessful()) {
                    Toast.makeText(applicationContext,"Successfully Inserted",
                        Toast.LENGTH_SHORT).show()
                    finish()

                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " +
                        t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}