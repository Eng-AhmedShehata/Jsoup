package com.ashehata.jsoupapp.exam

import android.util.Log
import com.ashehata.jsoupapp.externals.*
import com.ashehata.jsoupapp.models.UserLogin
import org.jsoup.Connection
import org.jsoup.Jsoup

class ExamRepository {

    fun getExamList(url: String, cookies: Map<String, String>?): Connection.Response? {

        // Make the request
        return Jsoup.connect(url)
            .userAgent(USER_AGENT)
            .method(Connection.Method.GET) // POST method
            .followRedirects(true)
            .referrer(URL_BASE) // Pass login url
            .cookies(cookies) // Pass the previous cookies to complete login
            .ignoreHttpErrors(true)
            .timeout(0)// Infinite time out
            .execute()

    }
}