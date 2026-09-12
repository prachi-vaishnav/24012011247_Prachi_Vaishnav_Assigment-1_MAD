package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FlashcardsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcards)

        val notesEditText =
            findViewById<EditText>(R.id.flashcardNotesEditText)

        val countSpinner =
            findViewById<Spinner>(R.id.flashcardCountSpinner)

        val generateButton =
            findViewById<Button>(R.id.btnGenerateFlashcards)

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
    }
}