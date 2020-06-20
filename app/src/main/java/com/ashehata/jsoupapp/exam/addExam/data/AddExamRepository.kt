package com.ashehata.jsoupapp.exam.addExam.data

import com.ashehata.jsoupapp.models.Exam
import com.ashehata.jsoupapp.models.UserLogin
import org.jsoup.Connection

class AddExamRepository(private val remoteData: AddExamRemoteData,
                        private val localData: AddExamLocalData
                        ) {
    /**
     * To get cookies from exam page
     */
    fun getExamDocument(url: String) = remoteData.getExamDocument(url, localData.getCookies())

    /**
     *  To make post request for exam data (type & des)
     */
    fun addExam(url: String, exam: Exam, response: Connection.Response?)
            = remoteData.addExam(url, exam, response, localData.getCookies())

    /**
     * To save cookies to shared pref
     */
    /*
    fun saveCookies(cookies: HashMap<String, String>?)
            = localData.saveCookiesToShared(cookies)

     */
}