package com.example.petme.Login

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    var pref: SharedPreferences
    var edior: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0

    constructor(context: Context) {
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        edior = pref.edit()
    }
    companion object {
        val PREF_NAME: String = "SessionDemo"
        val IS_LOGIN: String = "isLogin"
        val KEY_PICTURE_PROFILE : String = "picture_profile"
        val KEY_USERNAME: String = "username"
        val KEY_EMAIL: String = "email"
        val KEY_TEL: String = "tel"
        val KEY_ADDRESS: String = "address"
        val KEY_USERID: String = "user_id"
        val KEY_PASSWORD: String = "password"
        val KEY_VERIFYID: String = "verify_id"
        val KEY_EXP: String = "expdate"
    }

    fun createLoginSession( picture_profile : String, username: String, email: String, tel: String,address: String, user_id: String ,password:String, verify_id: String, expdate: String) {
        edior.putBoolean(IS_LOGIN, true)
        edior.putString(KEY_PICTURE_PROFILE, picture_profile)
        edior.putString(KEY_USERNAME, username)
        edior.putString(KEY_EMAIL, email)
        edior.putString(KEY_TEL, tel)
        edior.putString(KEY_ADDRESS, address)
        edior.putString(KEY_USERID, user_id)
        edior.putString(KEY_PASSWORD, password)
        edior.putString(KEY_VERIFYID, verify_id)
        edior.putString(KEY_EXP, expdate)
        edior.commit()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }
}
