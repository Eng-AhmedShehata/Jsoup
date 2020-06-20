package com.ashehata.jsoupapp.login.data

import com.ashehata.jsoupapp.models.UserLogin
import org.jsoup.Connection

class LoginRepository(private val localData: LoginLocalData,
                      private val remoteData: LoginRemoteData
                      )  {

    /**
     * To get cookies from login page
     */
    fun getLoginDocument(url: String) = remoteData.getLoginDocument(url)

    /**
     *  To make post request for user data (name & pass)
     */
    fun login(url: String, userLogin: UserLogin, response: Connection.Response?)
            = remoteData.login(url, userLogin, response)

    /**
     * To save cookies to shared pref
     */
    fun saveCookies(cookies: HashMap<String, String>?)
            = localData.saveCookiesToShared(cookies)

}