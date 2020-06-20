package com.ashehata.jsoupapp.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashehata.jsoupapp.externals.ResponseTypes
import com.ashehata.jsoupapp.login.domain.LoginUseCase
import com.ashehata.jsoupapp.login.domain.LoginViewState
import com.ashehata.jsoupapp.models.UserLogin

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

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

    private fun getResponse(userLoginModel: UserLogin) {
        // Get the data
        useCase.getResponse(viewModelScope,getCurrentState(), userLoginModel) {
            _viewState.postValue(it)
        }
    }

    fun login(userLoginModel: UserLogin) {
        // Show progress bar
        _viewState.value = getCurrentState()?.copy(
            isLoading = true,
            responseType = ResponseTypes.EMPTY,
            cookies = null,
            errorMessage = null
            )

        // TODO validate the enterd data(Email & password)

        // Get response
        getResponse(userLoginModel)
    }
}