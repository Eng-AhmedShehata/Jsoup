package com.ashehata.jsoupapp.exam.displayExams.data

import android.content.SharedPreferences
import com.ashehata.jsoupapp.externals.SHARED_KEY_COOKIES
import com.ashehata.jsoupapp.login.data.LoginLocalData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class LocalData(private val sharedPreferences: SharedPreferences)  {

     open fun getCookies(): HashMap<String, String>? {

        val cookieString = sharedPreferences.getString(SHARED_KEY_COOKIES, "")
        val type = object : TypeToken<HashMap<String?, String?>?>() {}.type

        val cookie = with(Gson()) {
            val cookies: HashMap<String, String> =
                fromJson(cookieString, type)
            return@with cookies
        }

        return cookie
    }
}