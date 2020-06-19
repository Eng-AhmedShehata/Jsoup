package com.ashehata.jsoupapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.ashehata.jsoupapp.R
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamActivity
import com.ashehata.jsoupapp.externals.*
import com.ashehata.jsoupapp.models.UserLogin
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        updateUi()
        setLoginButton()

    }

    private fun updateUi() {
        viewModel.viewState.observe(this, Observer {
            // Show/Hide the progress
            pb_loading.visibility = if (it.isLoading!!) View.VISIBLE else View.GONE

            // Handle different response types
            when (it.responseType) {
                ResponseTypes.SUCCESSFUL -> { openExamActivity()}

                ResponseTypes.FAILED -> {
                  showMessage(getString(R.string.failed))
                }
                ResponseTypes.NOT_FOUND -> {
                    showMessage(getString(R.string.not_found))
                }
                else -> {}
            }



        })
    }

    private fun openExamActivity() {
        startActivity(Intent(this, ExamActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setLoginButton() {

        // Pass values to view model
        btn_login.setOnClickListener {
            // Get entered value (Email & password)
            val email = tl_email.editText?.text?.toString()?.trim()
            val password = tl_password.editText?.text?.toString()?.trim()
            Log.v("email", email + password)

            viewModel.login(UserLogin(email, password))
        }

    }
}