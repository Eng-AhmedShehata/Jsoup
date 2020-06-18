package com.ashehata.jsoupapp.login

import android.util.Log
import com.ashehata.jsoupapp.externals.*
import com.ashehata.jsoupapp.models.UserLogin
import org.jsoup.Connection
import org.jsoup.Jsoup

class LoginRepository : GlobalRepository {

    override fun getLoginDocument(url: String): Connection.Response?
            = Jsoup.connect(url).method(Connection.Method.GET).userAgent(USER_AGENT).execute()


    fun login(url: String, userLogin: UserLogin, response: Connection.Response?): Connection.Response? {

        // Get the current token
        val requestVerificationToken =
            response?.parse()?.select("input[name=__RequestVerificationToken]")?.get(0)?.`val`()

        val mData = mapOf(
            LOGIN_TOKEN to requestVerificationToken,
            LOGIN_EMAIL to userLogin.email,
            LOGIN_PASSWORD to userLogin.password )

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