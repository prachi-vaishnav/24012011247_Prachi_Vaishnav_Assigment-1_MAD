package com.example.smartstudenthub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AiDoubtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ai_doubt)

        val backButton = findViewById<TextView>(R.id.btnBack)
        val doubtEditText = findViewById<EditText>(R.id.doubtEditText)
        val askAIButton = findViewById<Button>(R.id.btnAskAI)
        val responseText = findViewById<TextView>(R.id.aiResponseText)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        askAIButton.setOnClickListener {

            val doubt = doubtEditText.text.toString().trim()

            if (doubt.isEmpty()) {

                responseText.text = "Please enter your doubt first."

            } else {

                responseText.text =
                    "AI response will appear here."
            }
        }
    }
}