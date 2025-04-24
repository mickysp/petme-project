package com.example.petme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.petme.Login.SessionManager
import com.example.petme.Pet.Adotp
import com.example.petme.Pet.Donate
import com.example.petme.Pet.DonateAPI
import com.example.petme.Pet.HomePetme
import com.example.petme.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var bindingDetail: ActivityDetailBinding
    lateinit var session: SessionManager
    val PetClient = PetAPI.create()
    var user : String = ""
    var setID : String = ""
    var verify : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        session = SessionManager(applicationContext)
        user=session.pref.getString(SessionManager.KEY_USERID,null).toString()
        verify=session.pref.getString(SessionManager.KEY_VERIFYID,null).toString()

        setID = intent.getStringExtra("mId").toString()
        val setName = intent.getStringExtra("mName")
        val setDistrict = intent.getStringExtra("mDistrict")
        val setGender = intent.getStringExtra("mGender")
        val setOld = intent.getStringExtra("mOld")
        val setMonth = intent.getStringExtra("mMonth")
        val setStatus = intent.getStringExtra("mStatus")
        val setType = intent.getStringExtra("mType")
        val setDetail = intent.getStringExtra("mDetail")
        val setImage = intent.getStringExtra("mImage")


        bindingDetail.namePet.text = "${setName}"
        bindingDetail.detailDistrict.text = "${setDistrict}"
        bindingDetail.detailGender.text = "เพศ: ${setGender}"
        bindingDetail.detailOld.text = "อายุ: ${setOld} ปี ${setMonth} เดือน"
        bindingDetail.detailStatus.text = "สถานะ: ${setStatus}"
        bindingDetail.detailType.text = "ประเภท: ${setType}"
        bindingDetail.detailDetail.text = "รายละเอียดเพิ่มเติม: ${setDetail}"
        Glide.with(applicationContext).load(setImage).into(bindingDetail.img1)
    }

    fun isadoptPet(v:View){
        val contextView : Context = v.context
        if (verify=="0"){
            Toast.makeText(applicationContext,"คุณยังไม่ได้ยืนยันตัวตน กรุณาไปที่หน้าบัญชีของคุณเพื่อทำการยืนยันตัวตนก่อน",Toast.LENGTH_LONG).show()
        }else{
            val myBuilder = AlertDialog.Builder(this)
            myBuilder.apply {
                setTitle("Warning")
                setMessage("Do you want to isadopt the pet?")
                setNegativeButton("Yes") { dialog, which ->
                    PetClient.isadoptPet(setID.toInt())
                        .enqueue(object : Callback<PetHome> {
                            override fun onResponse(call: Call<PetHome>, response: Response<PetHome>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(applicationContext, "Successfully Isadopted", Toast.LENGTH_LONG).show()
                                }
                            }
                            override fun onFailure(call: Call<PetHome>, t: Throwable) {
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                            }
                        })
                    finish()

                    PetClient.adotpPet(
                        setID.toInt(),
                        user.toInt()
                    )
                        .enqueue(object : Callback<Adotp> {
                            override fun onResponse(
                                call: Call<Adotp>,
                                response: retrofit2.Response<Adotp>
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

                            override fun onFailure(call: Call<Adotp>, t: Throwable) {
                                Toast.makeText(
                                    applicationContext, "Error onFailure " +
                                            t.message, Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                }
                setPositiveButton ("No"){ dialog, which ->dialog.cancel()}
                show()
            }
        }
    }
}
