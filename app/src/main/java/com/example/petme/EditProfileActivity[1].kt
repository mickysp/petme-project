package com.example.petme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.petme.Login.SessionManager
import com.example.petme.databinding.ActivityEditProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {
    private lateinit var bindingEditProfile: ActivityEditProfileBinding
    lateinit var session: SessionManager
    val createClient = UserAPI.create()
    var user:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingEditProfile = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(bindingEditProfile.root)

        session = SessionManager(applicationContext)
        user=session.pref.getString(SessionManager.KEY_USERID,null).toString()
        val username:String?=session.pref.getString(SessionManager.KEY_USERNAME,null)
        val email:String?=session.pref.getString(SessionManager.KEY_EMAIL,null)
        val tel:String?=session.pref.getString(SessionManager.KEY_TEL,null)
        val address:String?=session.pref.getString(SessionManager.KEY_ADDRESS,null)
        val picture_profile:String?=session.pref.getString(SessionManager.KEY_PICTURE_PROFILE,null)
        val password:String?=session.pref.getString(SessionManager.KEY_PASSWORD,null)


        bindingEditProfile.editProfileID.setText(user)
        bindingEditProfile.editProfileID.isEnabled = false
        bindingEditProfile.editProfileName.setText(username)
        bindingEditProfile.editProfileEmail.setText(email)
        bindingEditProfile.editProfilePhone.setText(tel)
        bindingEditProfile.editProfileAddress.setText(address)
        bindingEditProfile.editProfileImage.setText(picture_profile)
        bindingEditProfile.editProfilePassword.setText(password)
        bindingEditProfile.editProfilePassword.isEnabled = false

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun saveProfile(v: View){
        createClient.editUser (
            user.toInt(),
            bindingEditProfile.editProfileName.text.toString(),
            bindingEditProfile.editProfileEmail.text.toString(),
            bindingEditProfile.editProfilePhone.text.toString(),
            bindingEditProfile.editProfileAddress.text.toString(),
            bindingEditProfile.editProfileImage.text.toString(),
            bindingEditProfile.editProfilePassword.text.toString()).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                    finish()
                }else{ Toast.makeText(applicationContext,"Error ", Toast.LENGTH_SHORT).show() }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext,"Error onFailure " + t.message, Toast.LENGTH_LONG).show() }
        })
    }
}