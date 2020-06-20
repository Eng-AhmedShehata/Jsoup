package com.ashehata.jsoupapp.login.domain

import android.util.Log
import com.ashehata.jsoupapp.externals.*
import com.ashehata.jsoupapp.login.data.LoginRepository
import com.ashehata.jsoupapp.models.UserLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginUseCase(private val repository: LoginRepository) {


    fun getResponse(vmScope: CoroutineScope,
                    viewState: LoginViewState?,
                    userLoginModel: UserLogin,
                    updateViewState: (LoginViewState?) -> Unit) {

        // Start async to get response
        vmScope.launch(Dispatchers.IO) {
            try {
                // Get login document
                val firstResponse = repository.getLoginDocument(
                    URL_BASE + URL_LOGIN)

                // Make post request to login
                val loginResponse = repository.login(
                    URL_BASE + URL_LOGIN ,
                    userLoginModel,
                    firstResponse)

                val hasCookie = loginResponse?.hasCookie(".ASPXAUTH")
                Log.v("cookiesLogin", loginResponse?.cookies().toString())

                // Return the current view state
                when (loginResponse?.statusCode()) {

                    RESPONSE_SUCCESS -> {
                        if (hasCookie!!) {
                            // Save data to shared
                            repository.saveCookies(loginResponse.cookies() as HashMap<String, String>?)
                            // Update state
                            updateViewState(loginSuccessful(viewState, loginResponse.cookies()))
                        } else throw IllegalAccessException()
                    }
                    RESPONSE_FAILED -> {updateViewState(loginFailed(viewState))}
                    RESPONSE_NOT_FOUND -> {updateViewState(loginNotFound(viewState))}
                    else -> {updateViewState(loginFailed(viewState))}

                }

            } catch (e: Exception) {

                //Log.v("errrorrr", e.message)
                // Return the current view state
                updateViewState(
                    viewState?.copy(
                        cookies = null,
                        isLoading = false,
                        responseType = ResponseTypes.FAILED,
                        errorMessage = e.message)
                )
            }
        }




    }


    private fun loginNotFound(viewState: LoginViewState?): LoginViewState? {
        return viewState?.copy(
            null,
            false,
            ResponseTypes.NOT_FOUND)
    }

    private fun loginFailed(viewState: LoginViewState?): LoginViewState? {
        return viewState?.copy(
            null,
            false,
            ResponseTypes.FAILED)
    }

    private fun loginSuccessful(viewState: LoginViewState?, cookies: Map<String, String>?): LoginViewState? {
        return viewState?.copy(
            cookies = cookies as HashMap<String, String>?,
            isLoading =  false,
            responseType = ResponseTypes.SUCCESSFUL)
    }
}