package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddStudentActivity : AppCompatActivity() {

    private lateinit var db: StudentHelper
    private lateinit var nameText: EditText
    private lateinit var mailText: EditText
    private lateinit var passwText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_student)

        db = StudentHelper(this)
        nameText = findViewById(R.id.NameText)
        mailText = findViewById(R.id.MailText)
        passwText = findViewById(R.id.UsernameText)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val studentName = nameText.text.toString()
            val studentUsername = passwText.text.toString()
            val studentEmail = mailText.text.toString()

            if (isValidInput(studentName, studentUsername, studentEmail)) {
                val student = Student(0, studentName, studentUsername, studentEmail)
                db.insertStudent(student)
                finish()
                Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidInput(name: String, email: String, password: String): Boolean {
        if (name.isEmpty()) {
            showError(nameText, "Name is required")
            return false
        } else {
            clearError(nameText)
        }

        if (email.isEmpty()) {
            showError(mailText, "Email is required")
            return false

        } else {
            clearError(mailText)
        }

        if (password.isEmpty()) {
            showError(passwText, "Password is required")
            return false
        } else {
            clearError(passwText)
        }

        return true
    }

    private fun showError(editText: EditText, message: String) {
        editText.error = message
    }

    private fun clearError(editText: EditText) {
        editText.error = null
    }


}