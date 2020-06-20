package com.ashehata.jsoupapp.exam.addExam.displayExams

import com.ashehata.jsoupapp.externals.ResponseTypes
import com.ashehata.jsoupapp.models.Exam

data class ExamViewState(
    var examList: List<Exam>?,
    var isLoading: Boolean?,
    var responseType: ResponseTypes,
    var errorMessage: String?
)