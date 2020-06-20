package com.ashehata.jsoupapp.exam.addExam.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ashehata.jsoupapp.R
import com.ashehata.jsoupapp.externals.ResponseTypes
import com.ashehata.jsoupapp.externals.showMessage
import com.ashehata.jsoupapp.login.presentation.LoginViewModel
import com.ashehata.jsoupapp.models.Exam
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_add_exam.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddExamFragment : Fragment() {

    private val viewModel: AddExamViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_exam, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUi()
        setAddBtn()

    }

    private fun setAddBtn() {
        btn_add_exam.setOnClickListener {
            // Get entered value (Email & password)
            val type = tl_type.editText?.text?.toString()?.trim()
            val description = tl_description.editText?.text?.toString()?.trim()
            viewModel.addExam(Exam(type, description))
        }
    }

    private fun updateUi() {
        viewModel.viewState.observe(this, Observer {
            // Show/Hide the progress
            pb_loading_add_exam.visibility = if (it.isLoading!!) View.VISIBLE else View.GONE

            // Handle different response types
            when (it.responseType) {
                ResponseTypes.SUCCESSFUL -> { backToExams()}

                ResponseTypes.FAILED -> {
                    context?.showMessage(getString(R.string.failed))
                }
                ResponseTypes.NOT_FOUND -> {
                    context?.showMessage(getString(R.string.not_found))
                }
                else -> {}
            }



        })
    }

    private fun backToExams() {
        // To go to previous fragment(Exams list)
        findNavController().popBackStack()
    }
}