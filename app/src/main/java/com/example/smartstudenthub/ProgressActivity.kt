package com.example.smartstudenthub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val quizzesCompletedText =
            findViewById<TextView>(R.id.quizzesCompletedText)

        val questionsAttemptedText =
            findViewById<TextView>(R.id.questionsAttemptedText)

        val correctAnswersText =
            findViewById<TextView>(R.id.correctAnswersText)

        val accuracyText =
            findViewById<TextView>(R.id.accuracyText)

        val lastScoreText =
            findViewById<TextView>(R.id.lastScoreText)

        val sharedPreferences =
            getSharedPreferences("SmartStudentHub", MODE_PRIVATE)

        val quizzesCompleted =
            sharedPreferences.getInt("quizzesCompleted", 0)

        val questionsAttempted =
            sharedPreferences.getInt("questionsAttempted", 0)

        val correctAnswers =
            sharedPreferences.getInt("correctAnswers", 0)

        val lastScore =
            sharedPreferences.getInt("lastScore", 0)

        val lastTotal =
            sharedPreferences.getInt("lastTotal", 0)

        val accuracy =
            if (questionsAttempted > 0) {
                (correctAnswers * 100) / questionsAttempted
            } else {
                0
            }

        quizzesCompletedText.text =
            "Quizzes Completed: $quizzesCompleted"

        questionsAttemptedText.text =
            "Questions Attempted: $questionsAttempted"

        correctAnswersText.text =
            "Correct Answers: $correctAnswers"

        accuracyText.text =
            "Accuracy: $accuracy%"

        lastScoreText.text =
            "Last Quiz Score: $lastScore / $lastTotal"
    }
}