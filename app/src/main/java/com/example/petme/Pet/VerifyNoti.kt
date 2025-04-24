package com.example.petme.Pet

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VerifyNoti(
    @Expose
    @SerializedName("verify_id") val verify_id: Int,

    @Expose
    @SerializedName("text_noti") val text_noti: String
)
