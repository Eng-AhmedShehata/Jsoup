package com.ashehata.jsoupapp.exam.addExam.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashehata.jsoupapp.exam.addExam.domain.AddExamUseCase
import com.ashehata.jsoupapp.externals.ResponseTypes
import com.ashehata.jsoupapp.login.domain.LoginViewState
import com.ashehata.jsoupapp.models.Exam
import com.ashehata.jsoupapp.models.UserLogin

class AddExamViewModel(private val useCase: AddExamUseCase) : ViewModel() {

    /*
    *
    */
    private val _viewState = MutableLiveData<LoginViewState>()
    val viewState: LiveData<LoginViewState> = _viewState

    // To get current state
    private fun getCurrentState() = _viewState.value

    init {
        // init the first view state
        _viewState.value = LoginViewState(
            cookies = null,
            isLoading = false,
            responseType = ResponseTypes.EMPTY,
            isEmptyFields = null,
            errorMessage = null
        )
    }

    private fun getResponse(exam: Exam) {
        // Get the data
        useCase.getResponse(viewModelScope,getCurrentState(), exam) {
            _viewState.postValue(it)
        }
    }

    fun addExam(exam: Exam) {
        // Show progress bar
        _viewState.value = getCurrentState()?.copy(
            isLoading = true,
            responseType = ResponseTypes.EMPTY,
            cookies = null,
            errorMessage = null
        )

        // TODO validate the enterd data(Type & Description)

        // Get response
        getResponse(exam)
    }
}