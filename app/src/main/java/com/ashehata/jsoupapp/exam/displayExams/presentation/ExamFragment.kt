package com.ashehata.jsoupapp.exam.displayExams.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ashehata.jsoupapp.R
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamViewModel
import com.ashehata.jsoupapp.exam.addExam.displayExams.ExamsAdapter
import com.ashehata.jsoupapp.externals.ResponseTypes
import com.ashehata.jsoupapp.externals.showMessage
import kotlinx.android.synthetic.main.fragment_exam.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExamFragment : Fragment() {

    private val viewModel: ExamViewModel by viewModel()
    private lateinit var examsAdapter: ExamsAdapter
    private var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*
        // Inflate the view
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_exam, container, false)
            return mView
        }
        else return mView

         */
        return inflater.inflate(R.layout.fragment_exam, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getExamList()
        updateUi()
        addfabBtn()
    }

    private fun addfabBtn() {
        fab_add_exam.setOnClickListener {
            findNavController().navigate(R.id.action_add_new_exam)
        }
    }

    private fun updateUi() {
        viewModel.viewState.observe(this,  Observer {

            // Show/Hide the progress
            pb_loading_exams.visibility = if (it.isLoading!!) View.VISIBLE else View.GONE


            if (!it.examList.isNullOrEmpty()) {
                // Display exams list
//                Toast.makeText(context, it.examList?.size.toString(), Toast.LENGTH_SHORT).show()
                examsAdapter =
                    ExamsAdapter(it.examList!!)
                rv_exams.adapter = examsAdapter
            }

            // Handle different response types
            when (it.responseType) {
                ResponseTypes.SUCCESSFUL -> { }

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

    private fun getExamList() {
        viewModel.getExamsList()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(context, "Destroyed", Toast.LENGTH_SHORT).show()
    }
}