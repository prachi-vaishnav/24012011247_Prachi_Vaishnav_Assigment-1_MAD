package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ViewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)

        val titleText = findViewById<TextView>(R.id.viewNoteTitle)
        val contentText = findViewById<TextView>(R.id.viewNoteContent)
        val menuButton = findViewById<ImageButton>(R.id.noteMenuButton)

        val title = intent.getStringExtra("noteTitle") ?: ""
        val content = intent.getStringExtra("noteContent") ?: ""

        titleText.text = title
        contentText.text = content

        menuButton.setOnClickListener {

            val popupMenu = PopupMenu(this, menuButton)

            popupMenu.menu.add("Edit")
            popupMenu.menu.add("Delete")

            popupMenu.setOnMenuItemClickListener { item ->

                when (item.title.toString()) {

                    "Edit" -> {

                        val intent =
                            Intent(this, AddNoteActivity::class.java)

                        intent.putExtra("editMode", true)
                        intent.putExtra("oldTitle", title)
                        intent.putExtra("oldContent", content)

                        startActivity(intent)
                    }

                    "Delete" -> {

                        AlertDialog.Builder(this)
                            .setTitle("Delete Note")
                            .setMessage("Are you sure you want to delete this note?")
                            .setPositiveButton("Delete") { _, _ ->

                                deleteNote(title, content)

                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                }

                true
            }

            popupMenu.show()
        }
    }

    private fun deleteNote(title: String, content: String) {

        val sharedPreferences =
            getSharedPreferences("SmartStudentHub", MODE_PRIVATE)

        val savedNotes =
            sharedPreferences.getString("notes", "") ?: ""

        val notes = savedNotes.split("###").toMutableList()

        val noteToDelete = "$title|||$content"

        notes.remove(noteToDelete)

        val updatedNotes = notes.joinToString("###")

        sharedPreferences.edit()
            .putString("notes", updatedNotes)
            .apply()

        Toast.makeText(
            this,
            "Note deleted",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}