package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val notesEditText =
            findViewById<EditText>(R.id.quizNotesEditText)

        val countSpinner =
            findViewById<Spinner>(R.id.quizCountSpinner)

        val generateButton =
            findViewById<Button>(R.id.btnGenerateQuiz)

        val questionCounts = arrayOf("5", "10", "15")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            questionCounts
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        countSpinner.adapter = adapter

        generateButton.setOnClickListener {

            val notes = notesEditText.text.toString().trim()
            val numberOfQuestions = countSpinner.selectedItem.toString()

            if (notes.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter your notes first",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent =
                    Intent(this, GeneratedQuizActivity::class.java)

                intent.putExtra("notes", notes)
                intent.putExtra("numberOfQuestions", numberOfQuestions)

                startActivity(intent)
            }
        }
    }
}