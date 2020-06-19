package com.ashehata.jsoupapp.exam.displayExams

import com.ashehata.jsoupapp.externals.USER_AGENT
import org.jsoup.Connection
import org.jsoup.Jsoup

class RemoteData {

    fun getExamsList(url: String, cookies: Map<String, String>?): Connection.Response? {

        // Make the request
        return Jsoup.connect(url)
            .userAgent(USER_AGENT)
            .method(Connection.Method.GET) // POST method
            .followRedirects(true)
            .referrer(url) // Pass login url
            .cookies(cookies) // Pass the previous cookies to complete login
            .ignoreHttpErrors(true)
            .timeout(0)// Infinite time out
            .execute()

    }
}