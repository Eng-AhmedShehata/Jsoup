package com.ashehata.jsoupapp.exam.addExam.displayExams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.ashehata.jsoupapp.R
import com.ashehata.jsoupapp.login.presentation.LoginActivity

class ExamActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_logout) {
            showDialog()
        }
        return true
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.log_out))
            .setMessage(getString(R.string.do_u_log))
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                openLoginActivity()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
                dialog.dismiss()
            }.show()

    }

    private fun openLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}