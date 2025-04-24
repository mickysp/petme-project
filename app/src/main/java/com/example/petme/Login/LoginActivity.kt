package com.example.petme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.petme.Login.SessionManager
import com.example.petme.Pet.HomePetme
import com.example.petme.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingLogin: ActivityLoginBinding
    lateinit var session: SessionManager
    val createClient = UserAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingLogin = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingLogin.root)

        bindingLogin.loginBtn.setOnClickListener {
            Login()
        }
        bindingLogin.mainSignup.setOnClickListener {
            Regis()
        }

        session = SessionManager(applicationContext)
        if (!session.pref.getString(SessionManager. KEY_EMAIL, null).isNullOrEmpty()){
            val email: String? = session.pref.getString(SessionManager. KEY_EMAIL, null)
            bindingLogin.loginEmail.setText(email)
        }
    }

    private fun Regis() {
        val intent = Intent(applicationContext,SignupActivity::class.java)
        startActivity(intent)
    }

    private fun Login() {
        var email = bindingLogin.loginEmail.text.toString()
        var password = bindingLogin.loginPassword.text.toString()
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(applicationContext,"กรุณากรอกข้อมูลก่อนเข้าสู่ระบบจ้า", Toast.LENGTH_LONG).show()
        }else{
            createClient.userLogin(email,password)
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        val success =response.body()?.success.toString().toInt()
                        if(success == 0){
                            Toast.makeText(applicationContext, "ชื่อหรือรหัสผ่านไม่ถูกต้องนะเตง", Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(applicationContext, "เข้าสู่ระบบสำเร็จแล้วจ้า", Toast.LENGTH_LONG).show()
                            val picture_ptofile = response.body()?.picture_profile.toString()
                            val user_id = response.body()?.user_id.toString()
                            val username = response.body()?.username.toString()
                            val tel = response.body()?.tel.toString()
                            val address = response.body()?.address.toString()
                            val verify_id = response.body()?.verify_id.toString()
                            val expdate = response.body()?.verify_id.toString()

                            session.createLoginSession(picture_ptofile, username, email, tel, address, user_id, password, verify_id,expdate)
                            var i: Intent = Intent(applicationContext, HomePetme::class.java)
                            startActivity(i)
                            finish() }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(applicationContext,"Error onFailure " + t.message,Toast.LENGTH_LONG).show()
                    }
                })

        } }
}
