package com.ashehata.jsoupapp.externals

import android.app.Application
import android.content.Context
import com.ashehata.jsoupapp.exam.addExam.data.AddExamLocalData
import com.ashehata.jsoupapp.exam.addExam.data.AddExamRemoteData
import com.ashehata.jsoupapp.exam.addExam.data.AddExamRepository
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamRepository
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamUseCase
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamViewModel
import com.ashehata.jsoupapp.exam.addExam.domain.AddExamUseCase
import com.ashehata.jsoupapp.exam.addExam.presentation.AddExamViewModel
import com.ashehata.jsoupapp.exam.displayExams.data.LocalData
import com.ashehata.jsoupapp.exam.displayExams.data.RemoteData
import com.ashehata.jsoupapp.login.data.LoginLocalData
import com.ashehata.jsoupapp.login.data.LoginRemoteData
import com.ashehata.jsoupapp.login.data.LoginRepository
import com.ashehata.jsoupapp.login.domain.LoginUseCase
import com.ashehata.jsoupapp.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mModule = module {

    // For login feature
    factory { LoginLocalData(get()) }
    factory { LoginRemoteData() }
    factory {
        LoginRepository(
            get(),
            get()
        )
    }
    factory { LoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }

    // For exams feature
    single { get<Application>().getSharedPreferences("shared", Context.MODE_PRIVATE) }
    factory { RemoteData() }
    factory { LocalData(get()) }
    factory { ExamRepository(get(), get()) }
    factory { ExamUseCase(get()) }
    viewModel { ExamViewModel(get()) }


    // For add exam feature
    factory { AddExamRemoteData() }
    factory { AddExamLocalData(get()) }
    factory { AddExamRepository(get(), get()) }
    factory { AddExamUseCase(get()) }
    viewModel { AddExamViewModel(get()) }
}