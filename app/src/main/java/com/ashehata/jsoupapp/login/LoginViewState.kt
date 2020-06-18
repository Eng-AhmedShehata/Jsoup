package com.ashehata.jsoupapp.login

import com.ashehata.jsoupapp.externals.ResponseTypes

data class LoginViewState(
    var data: String?,
    var isLoading: Boolean?,
    var responseType: ResponseTypes?,
    var errorMessage: String?,
    var isEmptyFields: String?
)