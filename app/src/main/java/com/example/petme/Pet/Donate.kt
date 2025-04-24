package com.example.petme.Pet

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Donate(
    @Expose
    @SerializedName("donate_id") val donate_id: Int,

    @Expose
    @SerializedName("user_id") val user_id: Int,

    @Expose
    @SerializedName("picture_pay") val picture_pay: String,

    @Expose
    @SerializedName("balance") val balance: Int,

    @Expose
    @SerializedName("last4digits") val last4digits: String,
)
