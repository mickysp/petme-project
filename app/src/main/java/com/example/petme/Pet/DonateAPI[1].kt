package com.example.petme.Pet

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface DonateAPI {

    @FormUrlEncoded
    @POST("insertDonate")
    fun insertDonate(
        @Field("user_id") user_id: Int,
        @Field("picture_pay") picture_pay: String,
        @Field("balance") balance: Int,
        @Field("last4digits") last4digits: String
    ): Call<Donate>
}