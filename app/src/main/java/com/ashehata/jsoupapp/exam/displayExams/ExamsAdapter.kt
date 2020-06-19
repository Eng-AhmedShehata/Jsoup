package com.ashehata.jsoupapp.exam.addExam.displayExams

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ashehata.jsoupapp.R
import com.ashehata.jsoupapp.models.Exam
import kotlinx.android.synthetic.main.exam_item.view.*

class ExamsAdapter(val mExamsList: List<Exam>) : RecyclerView.Adapter<ExamsAdapter.ExamViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ExamViewHolder(
            inflater,
            parent
        )
    }

    override fun getItemCount(): Int = mExamsList.size

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        val exam: Exam = mExamsList.get(position)
        holder.bind(exam)
    }

    class ExamViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.exam_item, parent, false)) {

        private var mType: TextView? = null
        private var mDescription: TextView? = null

        init {
            mType = itemView.tv_type
            mDescription = itemView.tv_description
        }

        fun bind(exam: Exam) {
            mType?.text = exam.type
            mDescription?.text = exam.description

        }


    }

}