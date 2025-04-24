package com.example.petme

import com.example.petme.Pet.VerifyNoti
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserAPI {
    @GET("alluser")
    fun retrieveStudent(): Call<List<User>>

    @GET("notiText1")
    fun showNoti1(): Call<List<VerifyNoti>>

    @GET("notiText2")
    fun showNoti2(): Call<List<VerifyNoti>>


    @FormUrlEncoded
    @POST("register")
    fun registerUser(@Field("username") username:String,
                     @Field("email") email:String,
                     @Field("tel") tel:String,
                     @Field("address") address:String,
                     @Field("password") password:String): Call<User>

    @FormUrlEncoded
    @POST("login")
    fun loginUser (@Field("email") email:String,
                   @Field("password") password:String): Call<User>


    @GET("login/{email}/{password}")
    fun userLogin(
        @Path("email") email: String,
        @Path("password") password: String
    ): Call<User>

    @FormUrlEncoded
    @PUT("editUser/{user_id}")
    fun editUser(
        @Path("user_id") user_id: Int,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("tel") tel: String,
        @Field("address") address: String,
        @Field("picture_profile") picture_profile : String,
        @Field("password") password : String) : Call<User>

    @FormUrlEncoded
    @PUT("editVerify/{user_id}")
    fun editVerify(
        @Path("user_id") user_id: Int,
        @Field("picture_iden") picture_iden: String,
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("licence_id") licence_id: String,
        @Field("birth") birth : String,
        @Field("expdate") expdate : String) : Call<User>



    companion object
    {
        fun create(): UserAPI
        {
            val userClient : UserAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI ::class.java)
            return userClient
        }
    }
}