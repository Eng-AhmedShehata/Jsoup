package com.ashehata.jsoupapp.login.data

import android.util.Log
import com.ashehata.jsoupapp.externals.LOGIN_EMAIL
import com.ashehata.jsoupapp.externals.LOGIN_PASSWORD
import com.ashehata.jsoupapp.externals.TOKEN
import com.ashehata.jsoupapp.externals.USER_AGENT
import com.ashehata.jsoupapp.models.UserLogin
import org.jsoup.Connection
import org.jsoup.Jsoup

class LoginRemoteData {

    fun getLoginDocument(url: String): Connection.Response?
            = Jsoup.connect(url).method(Connection.Method.GET).userAgent(USER_AGENT).execute()


    fun login(url: String, userLogin: UserLogin, response: Connection.Response?): Connection.Response? {

        // Get the current token
        val requestVerificationToken =
            response?.parse()?.select("input[name=__RequestVerificationToken]")?.get(0)?.`val`()

        val email = "teacher@gmail.com"
        val pass = "tttt"
        val mData = hashMapOf(
            TOKEN to requestVerificationToken,
            LOGIN_EMAIL to email,
            LOGIN_PASSWORD to pass)


        Log.v("auth", requestVerificationToken)

        // Make the request
        return Jsoup.connect(url)
            .data(mData)// Pass the login data (Name / pass / auth)
            .userAgent(USER_AGENT)
            .method(Connection.Method.POST) // POST method
            .followRedirects(true)
            .referrer(url) // Pass login url
            .cookies(response?.cookies()) // Pass the previous cookies to complete login
            .ignoreHttpErrors(true)
            .timeout(0)// Infinite time out
            .execute()

    }
}