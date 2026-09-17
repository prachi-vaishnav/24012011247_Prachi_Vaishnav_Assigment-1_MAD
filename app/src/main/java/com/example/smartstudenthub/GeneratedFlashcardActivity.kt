package com.example.smartstudenthub

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GeneratedFlashcardActivity : AppCompatActivity() {

    private lateinit var progressText: TextView
    private lateinit var questionText: TextView
    private lateinit var answerText: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var nextCardButton: Button
    private lateinit var previousCardButton: Button

    private var currentCard = 0

    private val questions = arrayOf(
        "What is a computer network?",
        "What is the main purpose of a network?",
        "What is a protocol?",
        "What is an IP address?",
        "What is the Internet?"
    )

    private val answers = arrayOf(
        "A computer network is a group of connected computers that communicate and share resources.",
        "The main purpose is to allow devices to communicate and share data and resources.",
        "A protocol is a set of rules used for communication between devices.",
        "An IP address is a unique address used to identify a device on a network.",
        "The Internet is a worldwide network of interconnected computer networks."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generated_flashcard)

        // Back Button
        val backButton = findViewById<TextView>(R.id.btnBack)

        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        progressText = findViewById(R.id.flashcardProgressText)
        questionText = findViewById(R.id.flashcardQuestion)
        answerText = findViewById(R.id.flashcardAnswer)
        showAnswerButton = findViewById(R.id.btnShowAnswer)
        nextCardButton = findViewById(R.id.btnNextCard)
        previousCardButton = findViewById(R.id.btnPreviousCard)

        showCard()

        showAnswerButton.setOnClickListener {
            answerText.visibility = TextView.VISIBLE
        }

        nextCardButton.setOnClickListener {

            if (currentCard < questions.size - 1) {
                currentCard++
                showCard()
            } else {
                questionText.text = "🎉 Flashcards Completed!"
                answerText.text = "You have completed all the flashcards."
                answerText.visibility = TextView.VISIBLE
                showAnswerButton.isEnabled = false
                nextCardButton.isEnabled = false
            }
        }

        previousCardButton.setOnClickListener {

            if (currentCard > 0) {
                currentCard--
                showCard()
            }
        }
    }

    private fun showCard() {

        progressText.text = "Card ${currentCard + 1} of ${questions.size}"
        questionText.text = questions[currentCard]
        answerText.text = answers[currentCard]
        answerText.visibility = TextView.GONE

        if (currentCard == 0) {
            previousCardButton.visibility = TextView.GONE
        } else {
            previousCardButton.visibility = TextView.VISIBLE
        }
    }
}