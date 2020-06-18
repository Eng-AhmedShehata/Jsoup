package com.ashehata.jsoupapp.login

import android.util.Log
import com.ashehata.jsoupapp.externals.*
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
                val firstResponse = repository.getLoginDocument(URL_LOGIN)

                // Make post request to login
                val response = repository.login(
                    URL_BASE + URL_LOGIN ,
                    userLoginModel,
                    firstResponse)

                /*
                Log.v("Status_code", "getResponse: ${response?.statusCode()}")
                Log.v("Status_message", "getResponse: ${response?.statusMessage()}")
                Log.v("login_response", response?.parse()?.body().toString())
                Log.v("login_url", response?.url().toString())

                 */

                // Return the current view state
                when (response?.statusCode()) {

                    RESPONSE_SUCCESS -> { updateViewState(loginSuccessful(viewState)) }
                    RESPONSE_FAILED -> {updateViewState(loginFailed(viewState))}
                    RESPONSE_NOT_FOUND -> {updateViewState(loginNotFound(viewState))}
                    else -> {updateViewState(loginFailed(viewState))}

                }

            } catch (e: Exception) {

                //Log.v("errrorrr", e.message)
                // Return the current view state
                updateViewState(
                    viewState?.copy(
                        null,
                        false,
                        ResponseTypes.FAILED,
                        e.message)
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

    private fun loginSuccessful(viewState: LoginViewState?): LoginViewState? {
        return viewState?.copy(
            "",
            false,
            ResponseTypes.SUCCESSFUL)
    }
}