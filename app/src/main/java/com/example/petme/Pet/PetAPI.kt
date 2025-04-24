package com.example.petme

import com.example.petme.Pet.Adotp
import com.example.petme.Pet.Donate
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PetAPI {
    @GET("allpet")
    fun retrievePet(): Call<List<PetHome>>

    @GET("petNoIsadopt")
    fun noIsadopt(): Call<List<PetHome>>


    @GET("search/{typeanimal}")
    fun searchPet(
        @Path("typeanimal") typeanimal: String
    ): Call<List<PetHome>>

    @FormUrlEncoded
    @POST("isadopt")
    fun adotpPet(@Field("petdetail_id") petdetail_id:Int,
                 @Field("user_id") user_id:Int): Call<Adotp>

    @FormUrlEncoded
    @PUT("pet/{id}")
    fun updatePet(
        @Path("id") id: Int,
        @Field("name") name: String,
        @Field("old") old: Int
    ): Call<PetHome>

    @PUT("isadopt/{petdetail_id}")
    fun isadoptPet(
        @Path("petdetail_id") petdetail_id: Int
    ): Call<PetHome>


    @DELETE("pet/{id}")
    fun deletePet(
        @Path("id") id: Int
    ): Call<PetHome>

    companion object {
        fun create(): PetAPI {
            val petClient: PetAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PetAPI::class.java)
            return petClient
        }
    }
}
