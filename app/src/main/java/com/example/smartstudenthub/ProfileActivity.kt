package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Back Button
        val backButton = findViewById<TextView>(R.id.btnBack)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Progress
        val progressButton =
            findViewById<LinearLayout>(R.id.btnProgress)

        progressButton.setOnClickListener {

            val intent =
                Intent(this, ProgressActivity::class.java)

            startActivity(intent)
        }

        // Settings
        val settingsButton =
            findViewById<LinearLayout>(R.id.btnSettings)

        settingsButton.setOnClickListener {

            val settingsText =
                findViewById<TextView>(R.id.settingsStatus)

            settingsText.text =
                "Settings will be available soon."
        }

        // About
        val aboutButton =
            findViewById<LinearLayout>(R.id.btnAbout)

        aboutButton.setOnClickListener {

            val aboutText =
                findViewById<TextView>(R.id.aboutStatus)

            aboutText.text =
                "Smart Student Hub helps students organize notes, practice with flashcards and quizzes, track progress, and study with Focus Room."
        }
    }
}