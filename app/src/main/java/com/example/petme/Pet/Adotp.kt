package com.example.petme.Pet

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Adotp(
    @Expose
    @SerializedName("petdetail_id") val petdetail_id: Int,

    @Expose
    @SerializedName("user_id") val user_id: Int
)
