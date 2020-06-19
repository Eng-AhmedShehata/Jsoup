package com.ashehata.jsoupapp.externals

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamRepository
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamUseCase
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamViewModel
import com.ashehata.jsoupapp.exam.displayExams.LocalData
import com.ashehata.jsoupapp.exam.displayExams.RemoteData
import com.ashehata.jsoupapp.login.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mModule = module {

    // For login feature
    factory { LoginLocalData(get()) }
    factory { LoginRemoteData() }
    factory { LoginRepository(get(), get()) }
    factory { LoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }

    // For exams feature
    single { get<Application>().getSharedPreferences("shared", Context.MODE_PRIVATE) }
    factory { RemoteData() }
    factory { LocalData(get()) }
    factory { ExamRepository(get(), get()) }
    factory { ExamUseCase(get()) }
    viewModel { ExamViewModel(get()) }
}