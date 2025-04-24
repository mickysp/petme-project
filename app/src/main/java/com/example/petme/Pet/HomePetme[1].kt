package com.example.petme.Pet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.petme.*
import com.example.petme.databinding.ActivityHomePetmeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePetme : AppCompatActivity() {
    private lateinit var bindingHome: ActivityHomePetmeBinding
    var petList = arrayListOf<PetHome>()
    val createClient = PetAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHome = ActivityHomePetmeBinding.inflate((layoutInflater))
        setContentView(bindingHome.root)

        bindingHome.recyclerView.adapter = PetAdapter(this.petList, applicationContext)
        bindingHome.recyclerView.layoutManager = GridLayoutManager(this, 2)
        bindingHome.recyclerView.addItemDecoration(
            DividerItemDecoration(
                bindingHome.recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        bindingHome.donateClick.setOnClickListener {
            val intent = Intent(applicationContext,DonateForm::class.java)
            startActivity(intent)
        }

        bindingHome.profile.setOnClickListener {
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        }
        bindingHome.notification.setOnClickListener {
            val intent = Intent(applicationContext, NotificationActivity::class.java)
            startActivity(intent)
        }
        bindingHome.aboutClick.setOnClickListener {
            val intent = Intent(applicationContext, AboutmeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        callPetData()
    }

    private fun callPetData() {
        bindingHome.edtSearch.text?.clear()
        petList.clear();
        createClient.noIsadopt()
        val serv: PetAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PetAPI::class.java)
        serv.noIsadopt()
            .enqueue(object : Callback<List<PetHome>> {
                override fun onResponse(
                    call: Call<List<PetHome>>,
                    response: Response<List<PetHome>>
                ) {
                    response.body()?.forEach {petList.add(
                        PetHome(
                            it.petdetail_id,it.picture_pet,it.old,it.month,it.district,it.admin_id,it.user_id,it.detail,it.name,it.typeanimal,it.gender,it.status_pet

                                    )
                        )
                    }
                    //Set Data to RecyclerView
                    bindingHome.recyclerView.adapter = PetAdapter(petList, applicationContext)
                    bindingHome.txtList.text = "Pet List : " + petList.size.toString()
                }

                override fun onFailure(call: Call<List<PetHome>>, t: Throwable) {
                    Toast.makeText(
                        applicationContext, "Error onFailure" + t.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    fun clickSearch(v:View) {
        petList.clear();
        if(bindingHome.edtSearch.text!!.isEmpty()){
            callPetData()
        }else {
            createClient.searchPet(bindingHome.edtSearch.text.toString())
            val serv: PetAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PetAPI::class.java)
            serv.searchPet(bindingHome.edtSearch.text.toString())
                .enqueue(object : Callback<List<PetHome>> {
                    override fun onResponse(
                        call: Call<List<PetHome>>,
                        response: Response<List<PetHome>>
                    ) {
                        response.body()?.forEach {
                            petList.add(
                                PetHome(
                                    it.petdetail_id,
                                    it.picture_pet,
                                    it.old,
                                    it.month,
                                    it.district,
                                    it.admin_id,
                                    it.user_id,
                                    it.detail,
                                    it.name,
                                    it.typeanimal,
                                    it.gender,
                                    it.status_pet
                                )
                            )
                        }
                        //Set Data to RecyclerView
                        bindingHome.recyclerView.adapter = PetAdapter(petList, applicationContext)
                        bindingHome.txtList.text = "Pet List : " + petList.size.toString()
                    }

                    override fun onFailure(call: Call<List<PetHome>>, t: Throwable) {
                        Toast.makeText(
                            applicationContext, "Error onFailure" + t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}
