package com.ashehata.jsoupapp.exam.addExam.displayExams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashehata.jsoupapp.externals.ResponseTypes

class ExamViewModel(private val useCase: ExamUseCase) : ViewModel() {

    /*
   *
   */
    private val _viewState = MutableLiveData<ExamViewState>()
    val viewState: LiveData<ExamViewState> = _viewState

    // To get current state
    private fun getCurrentState() = _viewState.value


    init {
        // init the first view state
        _viewState.value = ExamViewState(
            examList = null,
            isLoading = true,
            responseType = ResponseTypes.EMPTY,
            errorMessage = null
        )

        getExamsList()
    }


    fun getExamsList() {
        // Show progress bar
        _viewState.value = getCurrentState()?.copy(
            isLoading = true,
            responseType = ResponseTypes.EMPTY,
            errorMessage = null
        )
        // Get the data
        useCase.getExamList(viewModelScope,getCurrentState()) {
            _viewState.postValue(it)
        }
    }

    override fun onCleared() {
        Log.v("viewModel", "cleared")
        super.onCleared()
    }
}