package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val addNoteButton = findViewById<Button>(R.id.btnAddNote)

        addNoteButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val notesContainer = findViewById<LinearLayout>(R.id.notesContainer)
        val noNotesText = findViewById<TextView>(R.id.noNotesText)

        notesContainer.removeAllViews()

        val sharedPreferences =
            getSharedPreferences("SmartStudentHub", MODE_PRIVATE)

        val savedNotes =
            sharedPreferences.getString("notes", "") ?: ""

        if (savedNotes.isEmpty()) {

            noNotesText.visibility = TextView.VISIBLE

        } else {

            noNotesText.visibility = TextView.GONE

            val notes = savedNotes.split("###")

            for (note in notes) {

                val parts = note.split("|||", limit = 2)

                if (parts.size == 2) {

                    val noteTitle = TextView(this)
                    noteTitle.text = parts[0]
                    noteTitle.textSize = 18f
                    noteTitle.setTypeface(null, android.graphics.Typeface.BOLD)
                    noteTitle.setPadding(16, 16, 16, 4)

                    noteTitle.setOnClickListener {

                        val intent = Intent(this, ViewNoteActivity::class.java)

                        intent.putExtra("noteTitle", parts[0])
                        intent.putExtra("noteContent", parts[1])

                        startActivity(intent)
                    }
                    val noteContent = TextView(this)
                    noteContent.text = parts[1]
                    noteContent.textSize = 15f
                    noteContent.setPadding(16, 0, 16, 16)

                    notesContainer.addView(noteTitle)
                    notesContainer.addView(noteContent)
                }
            }
        }
    }
}