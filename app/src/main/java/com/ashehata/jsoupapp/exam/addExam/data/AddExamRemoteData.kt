package com.ashehata.jsoupapp.exam.addExam.data

import android.util.Log
import com.ashehata.jsoupapp.externals.*
import com.ashehata.jsoupapp.models.Exam
import org.jsoup.Connection
import org.jsoup.Jsoup

class AddExamRemoteData {

    fun getExamDocument(url: String,cookies: Map<String, String>?): Connection.Response?
            = Jsoup.connect(url).cookies(cookies).method(Connection.Method.GET).userAgent(USER_AGENT).execute()


    fun addExam(url: String,
                exam: Exam,
                response: Connection.Response?,
                cookies: MutableMap<String, String>?): Connection.Response? {

        // Get the current token
        val requestVerificationToken =
            response?.parse()?.select("input[name=__RequestVerificationToken]")?.get(0)?.`val`()

        val mData = hashMapOf(
            TOKEN to requestVerificationToken,
            EXAM_TYPE to exam.type,
            EXAM_DESCRIPTION to exam.description)


        Log.v("authAdd", requestVerificationToken)
        Log.v("cookiesAuth", cookies.toString())


        // Make the request
        return Jsoup.connect(url)
            .data(mData)// Pass the login data
            .userAgent(USER_AGENT)
            .method(Connection.Method.POST) // POST method
            .followRedirects(true)
            .referrer(url) // Pass login url
            .cookies(cookies) // Pass the previous cookies to complete login
            .ignoreHttpErrors(true)
            .timeout(0)// Infinite time out
            .execute()

    }
}