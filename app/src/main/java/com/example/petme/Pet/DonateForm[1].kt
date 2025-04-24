package com.example.petme.Pet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.petme.Login.SessionManager
import com.example.petme.PetAPI
import com.example.petme.R
import com.example.petme.databinding.ActivityDonateFormBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DonateForm : AppCompatActivity() {
    private lateinit var bindingInsert: ActivityDonateFormBinding
    lateinit var session: SessionManager
    var user:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInsert = ActivityDonateFormBinding.inflate(layoutInflater)
        setContentView(bindingInsert.root)

        session = SessionManager(applicationContext)
        user=session.pref.getString(SessionManager.KEY_USERID,null).toString()
    }

    fun addDonate(v: View) {
        val contextView : Context = v.context
        val api: DonateAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DonateAPI::class.java)
        api.insertDonate(
            user.toInt(),
            bindingInsert.donateImage.text.toString(),
            bindingInsert.donateMoney.text.toString().toInt(),
            bindingInsert.donateNum.text.toString()
        )
            .enqueue(object : Callback<Donate> {
                override fun onResponse(
                    call: Call<Donate>,
                    response: retrofit2.Response<Donate>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext, "Successfully Inserted",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                        contextView.startActivity(Intent(applicationContext, HomePetme::class.java))
                    } else {
                        Toast.makeText(
                            applicationContext, "Inserted Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Donate>, t: Throwable) {
                    Toast.makeText(
                        applicationContext, "Error onFailure " +
                                t.message, Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}

