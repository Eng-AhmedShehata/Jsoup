package com.ashehata.jsoupapp.exam.addExam.displayExams

import com.ashehata.jsoupapp.externals.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ExamUseCase(private val repository: ExamRepository) {

    fun getExamList(vmScope: CoroutineScope,
                    viewState: ExamViewState?,
                    updateViewState: (ExamViewState?) -> Unit) {

        // Start async to get response
        vmScope.launch(Dispatchers.IO) {
            try {

                // Make get request to get exams list
                val response = repository.getExams(
                    url = URL_BASE + URL_EXAM_LIST)

                val examsList = parseExamsList(response?.parse())

                if (!examsList?.isNullOrEmpty()!!) {
                    updateViewState(
                        viewState?.copy(
                            examList = examsList,
                            responseType = ResponseTypes.SUCCESSFUL,
                            isLoading = false))

                } else {
                    updateViewState(
                        viewState?.copy(
                            examList = null,
                            responseType = ResponseTypes.FAILED,
                            isLoading = false))
                }


            } catch (e: Exception) {
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
}