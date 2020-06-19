package com.ashehata.jsoupapp.login

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.ashehata.jsoupapp.externals.SHARED_KEY_COOKIES
import com.google.gson.Gson


class LoginLocalData(private val sharedPreferences: SharedPreferences) {

    fun saveCookiesToShared(cookies: HashMap<String, String>?) {

        // Convert to string using gson

        val cookiesString = with(Gson()) {
            return@with this.toJson(cookies)
        }

        // Save in shared pref
        sharedPreferences.edit().putString(SHARED_KEY_COOKIES, cookiesString).apply()
    }
}