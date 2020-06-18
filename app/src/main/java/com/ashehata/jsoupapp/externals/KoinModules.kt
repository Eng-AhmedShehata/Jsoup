package com.ashehata.jsoupapp.externals

import android.widget.Toast
import com.ashehata.jsoupapp.login.LoginRepository
import com.ashehata.jsoupapp.login.LoginUseCase
import com.ashehata.jsoupapp.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mModule = module {

    factory { LoginRepository() }
    factory { LoginUseCase(get()) }
    viewModel { LoginViewModel(get()) }
}