package com.ashehata.jsoupapp.exam.addExam.domain

import android.util.Log
import com.ashehata.jsoupapp.exam.addExam.data.AddExamRepository
import com.ashehata.jsoupapp.externals.*
import com.ashehata.jsoupapp.login.domain.LoginViewState
import com.ashehata.jsoupapp.models.Exam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AddExamUseCase(private val repository: AddExamRepository) {
    fun getResponse(vmScope: CoroutineScope,
                    viewState: LoginViewState?,
                    exam: Exam,
                    updateViewState: (LoginViewState?) -> Unit) {

        // Start async to get response
        vmScope.launch(Dispatchers.IO) {
            try {
                // Get login document
                val firstResponse = repository.getExamDocument(
                    URL_BASE + URL_EXAM_ADD
                )
                Log.v("firstResponse", firstResponse?.statusCode().toString())
                Log.v("firstResponse", firstResponse?.url().toString())

                // Make post request to login
                val response = repository.addExam(
                    URL_BASE + URL_EXAM_ADD ,
                    exam,
                    firstResponse)

                Log.v("addResponse", response?.statusCode().toString())
                Log.v("addResponse", response?.url().toString())
                Log.v("addResponse", response?.body()!!)


                // Return the current view state
                when (response.statusCode()) {

                    RESPONSE_SUCCESS -> {
                        updateViewState(addSuccessful(viewState, response.cookies()))
                    }
                    RESPONSE_FAILED -> {updateViewState(failed(viewState))}
                    RESPONSE_NOT_FOUND -> {updateViewState(notFound(viewState))}
                    else -> {updateViewState(failed(viewState))}

                }

            } catch (e: Exception) {
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


    private fun notFound(viewState: LoginViewState?): LoginViewState? {
        return viewState?.copy(
            null,
            false,
            ResponseTypes.NOT_FOUND)
    }

    private fun failed(viewState: LoginViewState?): LoginViewState? {
        return viewState?.copy(
            null,
            false,
            ResponseTypes.FAILED)
    }

    private fun addSuccessful(viewState: LoginViewState?, cookies: Map<String, String>?): LoginViewState? {
        return viewState?.copy(
            cookies = cookies as HashMap<String, String>?,
            isLoading =  false,
            responseType = ResponseTypes.SUCCESSFUL)
    }

}