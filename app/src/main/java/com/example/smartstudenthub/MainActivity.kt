package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Profile Button
        val profileButton = findViewById<TextView>(R.id.profileButton)

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Quick Access - Notes
        val notesButton = findViewById<LinearLayout>(R.id.btnNotes)

        notesButton.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        // Quick Access - Flashcards
        val flashcardsButton = findViewById<LinearLayout>(R.id.btnFlashcards)

        flashcardsButton.setOnClickListener {
            val intent = Intent(this, FlashcardsActivity::class.java)
            startActivity(intent)
        }

        // Quick Access - Quiz
        val quizButton = findViewById<LinearLayout>(R.id.btnQuiz)

        quizButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation - Notes
        val navNotes = findViewById<LinearLayout>(R.id.navNotes)

        navNotes.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation - Flashcards
        val navFlashcards = findViewById<LinearLayout>(R.id.navFlashcards)

        navFlashcards.setOnClickListener {
            val intent = Intent(this, FlashcardsActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation - Quiz
        val navQuiz = findViewById<LinearLayout>(R.id.navQuiz)

        navQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
        // Bottom Navigation - Focus Room
        val navFocus = findViewById<LinearLayout>(R.id.navFocus)

        navFocus.setOnClickListener {
            val intent = Intent(this, FocusRoomActivity::class.java)
            startActivity(intent)
        }
        // Quick Access - AI Doubt Solver
        val aiDoubtButton = findViewById<LinearLayout>(R.id.btnAIDoubt)

        aiDoubtButton.setOnClickListener {
            val intent = Intent(this, AiDoubtActivity::class.java)
            startActivity(intent)
        }
        // Quick Access - Focus Room
        val focusRoomButton = findViewById<LinearLayout>(R.id.btnFocusRoom)

        focusRoomButton.setOnClickListener {
            val intent = Intent(this, FocusRoomActivity::class.java)
            startActivity(intent)
        }
    }
}