package com.example.smartstudenthub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val titleEditText =
            findViewById<EditText>(R.id.noteTitleEditText)

        val contentEditText =
            findViewById<EditText>(R.id.noteContentEditText)

        val saveButton =
            findViewById<Button>(R.id.saveNoteButton)

        val editMode =
            intent.getBooleanExtra("editMode", false)

        val oldTitle =
            intent.getStringExtra("oldTitle") ?: ""

        val oldContent =
            intent.getStringExtra("oldContent") ?: ""

        // If editing, show the existing note
        if (editMode) {

            titleEditText.setText(oldTitle)
            contentEditText.setText(oldContent)

            saveButton.text = "Update Note"
        }

        saveButton.setOnClickListener {

            val title =
                titleEditText.text.toString().trim()

            val content =
                contentEditText.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter title and note",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val sharedPreferences =
                    getSharedPreferences(
                        "SmartStudentHub",
                        MODE_PRIVATE
                    )

                val savedNotes =
                    sharedPreferences.getString(
                        "notes",
                        ""
                    ) ?: ""

                val notes =
                    savedNotes.split("###").toMutableList()

                if (editMode) {

                    // Remove old version
                    val oldNote =
                        "$oldTitle|||$oldContent"

                    notes.remove(oldNote)

                }

                // Add updated version
                val newNote =
                    "$title|||$content"

                notes.add(newNote)

                val updatedNotes =
                    notes.joinToString("###")

                sharedPreferences.edit()
                    .putString(
                        "notes",
                        updatedNotes
                    )
                    .apply()

                if (editMode) {

                    Toast.makeText(
                        this,
                        "Note updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    Toast.makeText(
                        this,
                        "Note saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                finish()
            }
        }
    }
}