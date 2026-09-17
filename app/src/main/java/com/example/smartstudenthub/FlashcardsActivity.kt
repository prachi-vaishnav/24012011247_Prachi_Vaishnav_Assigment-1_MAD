package com.example.smartstudenthub

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FlashcardsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcards)

        // Back Button
        val backButton = findViewById<TextView>(R.id.btnBack)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Notes Input
        val notesEditText =
            findViewById<EditText>(R.id.flashcardNotesEditText)

        // Number of Cards Spinner
        val countSpinner =
            findViewById<Spinner>(R.id.flashcardCountSpinner)

        // Generate Button
        val generateButton =
            findViewById<Button>(R.id.btnGenerateFlashcards)

        // Available card counts
        val cardCounts = arrayOf("5", "10", "15")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            cardCounts
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        countSpinner.adapter = adapter

        // Generate Flashcards
        generateButton.setOnClickListener {

            val notes = notesEditText.text.toString().trim()
            val numberOfCards = countSpinner.selectedItem.toString()

            if (notes.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter your notes first",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val intent =
                    Intent(this, GeneratedFlashcardActivity::class.java)

                intent.putExtra("notes", notes)
                intent.putExtra("numberOfCards", numberOfCards)

                startActivity(intent)
            }
        }

        // Bottom Navigation - Home
        val navHome =
            findViewById<LinearLayout>(R.id.navHome)

        navHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation - Notes
        val navNotes =
            findViewById<LinearLayout>(R.id.navNotes)

        navNotes.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation - Flashcards
        val navFlashcards =
            findViewById<LinearLayout>(R.id.navFlashcards)

        navFlashcards.setOnClickListener {
            // Already on Flashcards
        }

        // Bottom Navigation - Quiz
        val navQuiz =
            findViewById<LinearLayout>(R.id.navQuiz)

        navQuiz.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation - Focus Room
        val navFocus =
            findViewById<LinearLayout>(R.id.navFocus)

        navFocus.setOnClickListener {
            val intent = Intent(this, FocusRoomActivity::class.java)
            startActivity(intent)
        }
    }
}