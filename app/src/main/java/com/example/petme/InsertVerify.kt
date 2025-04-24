package com.example.petme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.petme.Login.SessionManager
import com.example.petme.databinding.ActivityInsertVerifyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertVerify : AppCompatActivity() {
    private lateinit var bindingVerify: ActivityInsertVerifyBinding
    lateinit var session: SessionManager
    val createClient = UserAPI.create()
    var user:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        bindingVerify = ActivityInsertVerifyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bindingVerify.root)

        session = SessionManager(applicationContext)
        user=session.pref.getString(SessionManager.KEY_USERID,null).toString()



    }
    fun submitBtn(v: View){
        createClient.editVerify (
            user.toInt(),
            bindingVerify.verifyImage.text.toString(),
            bindingVerify.verifyName.text.toString(),
            bindingVerify.verifySurname.text.toString(),
            bindingVerify.verifyIden.text.toString(),
            bindingVerify.verifyBirthday.text.toString(),
            bindingVerify.verifyExp.text.toString()).enqueue(object :
            Callback<User> {
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
