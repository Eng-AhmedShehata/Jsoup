package com.ashehata.jsoupapp.login.domain

import com.ashehata.jsoupapp.externals.ResponseTypes

data class LoginViewState(
    var cookies: HashMap<String, String>?,
    var isLoading: Boolean?,
    var responseType: ResponseTypes?,
    var errorMessage: String?,
    var isEmptyFields: String?
)