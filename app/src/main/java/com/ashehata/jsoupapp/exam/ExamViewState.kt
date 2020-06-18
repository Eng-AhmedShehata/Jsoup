package com.ashehata.jsoupapp.exam

import android.graphics.ColorSpace

data class ExamViewState(
    var examList: List<ColorSpace.Model>?,
    var isLoading: Boolean?,
    var errorMessage: String?
)