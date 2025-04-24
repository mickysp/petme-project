package com.example.petme

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("success") val success: Int,

    @Expose
    @SerializedName("user_id") val user_id: Int,

    @Expose
    @SerializedName("username") val username: String,

    @Expose
    @SerializedName("password") val password: String,

    @Expose
    @SerializedName("email") val email: String,

    @Expose
    @SerializedName("address") val address: String,

    @Expose
    @SerializedName("tel") val tel: String,

    @Expose
    @SerializedName("picture_profile") val picture_profile: String,

    @Expose
    @SerializedName("picture_iden") val picture_iden: String,

    @Expose
    @SerializedName("name") val name: String,

    @Expose
    @SerializedName("surname") val surname: String,

    @Expose
    @SerializedName("licence_id") val licence_id: String,

    @Expose
    @SerializedName("birth") val birth: String,

    @Expose
    @SerializedName("expdate") val expdate: String,

    @Expose
    @SerializedName("verify_id") val verify_id: Int





)
