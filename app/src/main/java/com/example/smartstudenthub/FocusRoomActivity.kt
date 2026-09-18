package com.example.smartstudenthub

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.LinearLayout

class FocusRoomActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var timerModeText: TextView
    private lateinit var statusText: TextView
    private lateinit var startPauseButton: Button
    private lateinit var resetButton: Button
    private lateinit var sessionsText: TextView
    private lateinit var totalTimeText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_room)

        // Back Button
        val backButton = findViewById<TextView>(R.id.btnBack)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Views
        timerText = findViewById(R.id.txtTimer)
        timerModeText = findViewById(R.id.txtTimerMode)
        statusText = findViewById(R.id.txtSessionStatus)

        startPauseButton = findViewById(R.id.btnStartPause)
        resetButton = findViewById(R.id.btnReset)

        sessionsText = findViewById(R.id.txtSessionsCompleted)
        totalTimeText = findViewById(R.id.txtTotalTime)

        val workButton = findViewById<Button>(R.id.btnWorkMode)
        val shortBreakButton = findViewById<Button>(R.id.btnShortBreak)
        val longBreakButton = findViewById<Button>(R.id.btnLongBreak)

        // Study Category
        val categorySpinner =
            findViewById<Spinner>(R.id.spinnerCategory)

        val categories = arrayOf(
            "CAO",
            "CN",
            "ML",
            "CNS",
            "MAD",
            "Aptitude",
            "Other"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        categorySpinner.adapter = adapter

        // Load saved statistics
        loadStatistics()

        // Show current timer
        updateTimerDisplay()

        // Work Focus
        workButton.setOnClickListener {
            FocusTimerManager.setMode(
                "Work Focus",
                25 * 60 * 1000L
            )

            updateTimerDisplay()
            statusText.text = "Ready to focus"
            startPauseButton.text = "Start Focus"
        }

        // Short Break
        shortBreakButton.setOnClickListener {
            FocusTimerManager.setMode(
                "Short Break",
                5 * 60 * 1000L
            )

            updateTimerDisplay()
            statusText.text = "Ready to relax"
            startPauseButton.text = "Start Break"
        }

        // Long Break
        longBreakButton.setOnClickListener {
            FocusTimerManager.setMode(
                "Long Break",
                15 * 60 * 1000L
            )

            updateTimerDisplay()
            statusText.text = "Ready to relax"
            startPauseButton.text = "Start Break"
        }

        // Start / Pause / Resume
        startPauseButton.setOnClickListener {

            if (FocusTimerManager.timerRunning) {

                FocusTimerManager.pause()

                startPauseButton.text = "Resume"
                statusText.text = "Session paused"

            } else {

                startTimer()
            }
        }

        // Reset
        resetButton.setOnClickListener {

            FocusTimerManager.reset()

            updateTimerDisplay()

            startPauseButton.text = "Start Focus"
            statusText.text = "Ready to focus"
        }
        // Bottom Navigation - Home
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

// Bottom Navigation - Notes
        val navNotes = findViewById<LinearLayout>(R.id.navNotes)
        navNotes.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

// Bottom Navigation - Flashcards
        val navFlashcards = findViewById<LinearLayout>(R.id.navFlashcards)
        navFlashcards.setOnClickListener {
            startActivity(Intent(this, FlashcardsActivity::class.java))
        }

// Bottom Navigation - Quiz
        val navQuiz = findViewById<LinearLayout>(R.id.navQuiz)
        navQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

// Bottom Navigation - Focus Room
        val navFocus = findViewById<LinearLayout>(R.id.navFocus)
        navFocus.setOnClickListener {
            // Already on Focus Room
        }
    }

    // ---------------- START TIMER ----------------

    private fun startTimer() {

        FocusTimerManager.start(

            onTick = {
                runOnUiThread {
                    updateTimerDisplay()
                }
            },

            onFinish = {

                runOnUiThread {

                    updateTimerDisplay()

                    startPauseButton.text = "Start Focus"

                    statusText.text =
                        "Session completed 🎉"

                    if (FocusTimerManager.currentMode == "Work Focus") {
                        saveCompletedSession()
                    }

                    playCompletionSound()
                }
            }
        )

        startPauseButton.text = "Pause"

        statusText.text =
            "Focus session running..."
    }

    // ---------------- TIMER DISPLAY ----------------

    private fun updateTimerDisplay() {

        val timeLeft =
            FocusTimerManager.timeLeftInMillis

        val minutes =
            (timeLeft / 1000) / 60

        val seconds =
            (timeLeft / 1000) % 60

        timerText.text =
            String.format(
                "%02d:%02d",
                minutes,
                seconds
            )

        timerModeText.text =
            FocusTimerManager.currentMode.uppercase()
    }

    // ---------------- SAVE SESSION ----------------

    private fun saveCompletedSession() {

        val sharedPreferences =
            getSharedPreferences(
                "SmartStudentHub",
                MODE_PRIVATE
            )

        val sessions =
            sharedPreferences.getInt(
                "focusSessions",
                0
            )

        val totalMinutes =
            sharedPreferences.getInt(
                "totalStudyMinutes",
                0
            )

        sharedPreferences.edit()
            .putInt(
                "focusSessions",
                sessions + 1
            )
            .putInt(
                "totalStudyMinutes",
                totalMinutes + 25
            )
            .apply()

        loadStatistics()
    }

    // ---------------- LOAD STATISTICS ----------------

    private fun loadStatistics() {

        val sharedPreferences =
            getSharedPreferences(
                "SmartStudentHub",
                MODE_PRIVATE
            )

        val sessions =
            sharedPreferences.getInt(
                "focusSessions",
                0
            )

        val totalMinutes =
            sharedPreferences.getInt(
                "totalStudyMinutes",
                0
            )

        sessionsText.text =
            sessions.toString()

        totalTimeText.text =
            "$totalMinutes min"
    }

    // ---------------- COMPLETION SOUND ----------------

    private fun playCompletionSound() {

        try {

            val mediaPlayer =
                MediaPlayer.create(
                    this,
                    android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
                )

            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                it.release()
            }

        } catch (e: Exception) {
            // Ignore sound errors
        }
    }

    // ---------------- REFRESH WHEN RETURNING ----------------

    override fun onResume() {
        super.onResume()

        updateTimerDisplay()

        if (FocusTimerManager.timerRunning) {
            startPauseButton.text = "Pause"
            statusText.text = "Focus session running..."
        }
    }
}