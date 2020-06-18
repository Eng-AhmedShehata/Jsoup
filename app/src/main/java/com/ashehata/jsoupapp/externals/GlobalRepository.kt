package com.ashehata.jsoupapp.externals

import org.jsoup.Connection
import org.jsoup.nodes.Document


interface GlobalRepository {

    fun getLoginDocument(url: String) : Connection.Response?

}