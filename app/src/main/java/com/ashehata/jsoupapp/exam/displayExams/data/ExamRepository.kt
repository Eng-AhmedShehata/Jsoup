package com.ashehata.jsoupapp.exam.addExam.displayExams

import com.ashehata.jsoupapp.exam.displayExams.data.LocalData
import com.ashehata.jsoupapp.exam.displayExams.data.RemoteData

class ExamRepository(private val localData: LocalData, private val remoteData: RemoteData) {

    /**
     * To get cookies from shard preference database
     */
    private fun getCookies(): HashMap<String, String>? = localData.getCookies()

    /**
     * To get exams list from remote data
     */
    fun getExams(url: String) = remoteData.getExamsList(url, getCookies())

}