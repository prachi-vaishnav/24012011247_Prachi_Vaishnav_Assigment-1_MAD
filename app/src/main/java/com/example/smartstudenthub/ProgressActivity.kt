package com.example.smartstudenthub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {

    private lateinit var focusSessionsText: TextView
    private lateinit var studyTimeText: TextView
    private lateinit var quizzesCompletedText: TextView
    private lateinit var questionsAttemptedText: TextView
    private lateinit var correctAnswersText: TextView
    private lateinit var lastScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_progress)

        // Back Button
        val backButton =
            findViewById<TextView>(R.id.btnBack)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Find Statistics Views
        focusSessionsText =
            findViewById(R.id.txtFocusSessions)

        studyTimeText =
            findViewById(R.id.txtStudyTime)

        quizzesCompletedText =
            findViewById(R.id.txtQuizzesCompleted)

        questionsAttemptedText =
            findViewById(R.id.txtQuestionsAttempted)

        correctAnswersText =
            findViewById(R.id.txtCorrectAnswers)

        lastScoreText =
            findViewById(R.id.txtLastScore)

        loadProgress()
    }

    override fun onResume() {
        super.onResume()

        loadProgress()
    }

    private fun loadProgress() {

        val preferences =
            getSharedPreferences(
                "SmartStudentHub",
                MODE_PRIVATE
            )

        val focusSessions =
            preferences.getInt(
                "focusSessions",
                0
            )

        val totalStudyMinutes =
            preferences.getInt(
                "totalStudyMinutes",
                0
            )

        val quizzesCompleted =
            preferences.getInt(
                "quizzesCompleted",
                0
            )

        val questionsAttempted =
            preferences.getInt(
                "questionsAttempted",
                0
            )

        val correctAnswers =
            preferences.getInt(
                "correctAnswers",
                0
            )

        val lastScore =
            preferences.getInt(
                "lastScore",
                0
            )

        val lastTotal =
            preferences.getInt(
                "lastTotal",
                0
            )

        focusSessionsText.text =
            focusSessions.toString()

        studyTimeText.text =
            "$totalStudyMinutes min"

        quizzesCompletedText.text =
            quizzesCompleted.toString()

        questionsAttemptedText.text =
            questionsAttempted.toString()

        correctAnswersText.text =
            correctAnswers.toString()

        lastScoreText.text =
            if (lastTotal > 0) {
                "$lastScore / $lastTotal"
            } else {
                "No quiz yet"
            }
    }
}