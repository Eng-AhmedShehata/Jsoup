package com.ashehata.jsoupapp.externals

import com.ashehata.jsoupapp.models.Exam
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

/*
** To parse the document and return exams list
 */
fun parseExamsList(document: Document?): List<Exam>? {

    val table = document?.select("table")?.get(0)
    val tableRows = table?.select("tbody")?.select("tr")
    val examsList =  mutableListOf<Exam>()


    for (row: Element in tableRows!!) {
        val type = row.select("td").get(0).text()
        val description = row.select("td").get(1).text()
        val exam = Exam(type, description)
        examsList.add(exam)
    }

    return examsList
}
