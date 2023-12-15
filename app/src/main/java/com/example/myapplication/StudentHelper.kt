package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class StudentHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    companion object {
        private const val DATABASE_NAME = "student.db"
        private const val DATABASE_VERSION = 3

        private const val TABLE_NAME = "student_table"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "student_name"
        private const val COLUMN_USERNAME = "student_username"
        private const val COLUMN_EMAIL = "student_email"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val createEmployeeTableQuery = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_USERNAME TEXT, "+
                "$COLUMN_EMAIL TEXT); "

        db?.execSQL(createEmployeeTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropStudentTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropStudentTableQuery)
    }

    fun insertStudent(student: Student) {
        val db = writableDatabase
        val values = contentValuesOf().apply {
            put(COLUMN_NAME, student.student_name)
            put(COLUMN_EMAIL, student.student_mail)
            put(COLUMN_USERNAME, student.student_username)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllStudents(): List<Student> {
        val studentsList = mutableListOf<Student>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val student_name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val student_username = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME))
            val student_mail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))


            val student = Student(id, student_name, student_username, student_mail)
            studentsList.add(student)
        }
        cursor.close()
        db.close()
        return studentsList
    }

}