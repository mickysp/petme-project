package com.example.petme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.petme.Login.SessionManager
import com.example.petme.Pet.HomePetme
import com.example.petme.Pet.NotificationActivity
import com.example.petme.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var bindingProfile: ActivityProfileBinding
    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingProfile = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bindingProfile.root)

        bindingProfile.home.setOnClickListener {
            val intent = Intent(applicationContext, HomePetme::class.java)
            startActivity(intent)
        }

        bindingProfile.notification.setOnClickListener {
            val intent = Intent(applicationContext, NotificationActivity::class.java)
            startActivity(intent)
        }

        bindingProfile.editProfile.setOnClickListener {
            val intent = Intent(applicationContext, EditProfileActivity::class.java)
            startActivity(intent)
        }

        bindingProfile.verifyUser.setOnClickListener {
            val intent = Intent(applicationContext, InsertVerify::class.java)
            startActivity(intent)
        }

        session = SessionManager(applicationContext)
        val picture_profile:String?=session.pref.getString(SessionManager.KEY_PICTURE_PROFILE,null)
        val username:String?=session.pref.getString(SessionManager.KEY_USERNAME,null)
        val email:String?=session.pref.getString(SessionManager.KEY_EMAIL,null)

        Glide.with(applicationContext).load("$picture_profile").into(bindingProfile.imageProfile)
        bindingProfile.txtproname.text= "$username"
        bindingProfile.txtproemail.text="$email"

        bindingProfile.btnlogout.setOnClickListener {
            val edit = session.edior
            edit.clear()
            edit.commit()

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }
}
