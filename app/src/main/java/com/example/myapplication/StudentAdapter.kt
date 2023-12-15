package com.example.myapplication

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter     (
private var students: List<Student>,
private val context: Context
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private val db: StudentHelper = StudentHelper(context)

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NameTextView: TextView = itemView.findViewById(R.id.NameTextView)
        val MailTextView: TextView = itemView.findViewById(R.id.MailTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.students_items, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.NameTextView.text = student.student_name
        holder.MailTextView.text = student.student_mail


    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun refreshData(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }

}