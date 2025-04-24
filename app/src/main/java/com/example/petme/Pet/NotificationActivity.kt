package com.example.petme.Pet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petme.*
import com.example.petme.Login.SessionManager
import com.example.petme.databinding.ActivityNotificationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationActivity : AppCompatActivity() {
    private lateinit var bindingNoti: ActivityNotificationBinding
    lateinit var session: SessionManager
    var notiList = arrayListOf<VerifyNoti>()
    val createClient = UserAPI.create()
    var verify : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNoti = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(bindingNoti.root)

        session = SessionManager(applicationContext)
        verify=session.pref.getString(SessionManager.KEY_VERIFYID,null).toString()

        bindingNoti.profile.setOnClickListener {
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        }
        bindingNoti.home.setOnClickListener {
            val intent = Intent(applicationContext,HomePetme::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        callNotification()
    }
    fun callNotification(){
        notiList.clear();
        if (verify == "0"){
            bindingNoti.recyclerView.visibility = View.GONE
        }
        if (verify == "1"){
            bindingNoti.text.visibility = View.GONE
            bindingNoti.nonoti.visibility = View.GONE
            createClient.showNoti1().enqueue(object : Callback<List<VerifyNoti>> {
                override fun onResponse(call: Call<List<VerifyNoti>>,
                                        response: Response<List<VerifyNoti>>
                ) {

                    response.body()?.forEach {
                        notiList.add(VerifyNoti(
                            it.verify_id,
                            it.text_noti
                        ))
                    }
                    bindingNoti.recyclerView.adapter = NotificationAdapter(notiList,applicationContext)
                    bindingNoti.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    bindingNoti.recyclerView.addItemDecoration(
                        DividerItemDecoration(
                            bindingNoti.recyclerView.getContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )}
                override fun onFailure(call: Call<List<VerifyNoti>>, t: Throwable) {

                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }

        if (verify == "2"){
            bindingNoti.text.visibility = View.GONE
            bindingNoti.nonoti.visibility = View.GONE
            createClient.showNoti2().enqueue(object : Callback<List<VerifyNoti>> {
                override fun onResponse(call: Call<List<VerifyNoti>>,
                                        response: Response<List<VerifyNoti>>
                ) {

                    response.body()?.forEach {
                        notiList.add(VerifyNoti(
                            it.verify_id,
                            it.text_noti
                        ))
                    }
                    bindingNoti.recyclerView.adapter = NotificationAdapter(notiList,applicationContext)
                    bindingNoti.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                    bindingNoti.recyclerView.addItemDecoration(
                        DividerItemDecoration(
                            bindingNoti.recyclerView.getContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )}
                override fun onFailure(call: Call<List<VerifyNoti>>, t: Throwable) {

                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}
