package com.example.smartstudenthub

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GeneratedQuizActivity : AppCompatActivity() {

    private lateinit var progressText: TextView
    private lateinit var questionText: TextView
    private lateinit var resultText: TextView

    private lateinit var optionA: Button
    private lateinit var optionB: Button
    private lateinit var optionC: Button
    private lateinit var optionD: Button

    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    private var currentQuestion = 0
    private var score = 0
    private var answered = false

    private val questions = arrayOf(
        "What is a computer network?",
        "What is a protocol?",
        "What does IP stand for?",
        "Which device connects different networks?",
        "What is the Internet?"
    )

    private val options = arrayOf(
        arrayOf(
            "A group of connected computers",
            "A single computer",
            "A type of software",
            "A programming language"
        ),
        arrayOf(
            "A set of communication rules",
            "A computer",
            "A database",
            "A cable"
        ),
        arrayOf(
            "Internet Protocol",
            "Internal Program",
            "Internet Program",
            "Input Protocol"
        ),
        arrayOf(
            "Router",
            "Keyboard",
            "Monitor",
            "Printer"
        ),
        arrayOf(
            "A worldwide network of networks",
            "A single computer",
            "A mobile application",
            "A programming language"
        )
    )

    private val correctAnswers = intArrayOf(0, 0, 0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generated_quiz)

        progressText = findViewById(R.id.quizProgressText)
        questionText = findViewById(R.id.quizQuestion)
        resultText = findViewById(R.id.quizResultText)

        optionA = findViewById(R.id.optionA)
        optionB = findViewById(R.id.optionB)
        optionC = findViewById(R.id.optionC)
        optionD = findViewById(R.id.optionD)

        previousButton = findViewById(R.id.btnPreviousQuestion)
        nextButton = findViewById(R.id.btnNextQuestion)

        showQuestion()

        optionA.setOnClickListener {
            checkAnswer(0)
        }

        optionB.setOnClickListener {
            checkAnswer(1)
        }

        optionC.setOnClickListener {
            checkAnswer(2)
        }

        optionD.setOnClickListener {
            checkAnswer(3)
        }

        previousButton.setOnClickListener {
            if (currentQuestion > 0) {
                currentQuestion--
                showQuestion()
            }
        }

        nextButton.setOnClickListener {
            if (currentQuestion < questions.size - 1) {
                currentQuestion++
                showQuestion()
            } else {
                showFinalScore()
            }
        }
    }

    private fun showQuestion() {
        progressText.text =
            "Question ${currentQuestion + 1} of ${questions.size}"

        questionText.text = questions[currentQuestion]

        optionA.text = "A. ${options[currentQuestion][0]}"
        optionB.text = "B. ${options[currentQuestion][1]}"
        optionC.text = "C. ${options[currentQuestion][2]}"
        optionD.text = "D. ${options[currentQuestion][3]}"

        resultText.visibility = TextView.GONE

        optionA.isEnabled = true
        optionB.isEnabled = true
        optionC.isEnabled = true
        optionD.isEnabled = true

        answered = false

        if (currentQuestion == 0) {
            previousButton.visibility = TextView.GONE
        } else {
            previousButton.visibility = TextView.VISIBLE
        }
    }

    private fun checkAnswer(selectedAnswer: Int) {

        if (answered) {
            return
        }

        answered = true

        if (selectedAnswer == correctAnswers[currentQuestion]) {
            score++

            resultText.text = "✅ Correct!"
        } else {
            resultText.text = "❌ Incorrect!"
        }

        resultText.visibility = TextView.VISIBLE

        optionA.isEnabled = false
        optionB.isEnabled = false
        optionC.isEnabled = false
        optionD.isEnabled = false
    }

    private fun showFinalScore() {
        val sharedPreferences =
            getSharedPreferences("SmartStudentHub", MODE_PRIVATE)

        val quizzesCompleted =
            sharedPreferences.getInt("quizzesCompleted", 0)

        val questionsAttempted =
            sharedPreferences.getInt("questionsAttempted", 0)

        val correctAnswers =
            sharedPreferences.getInt("correctAnswers", 0)

        sharedPreferences.edit()
            .putInt("quizzesCompleted", quizzesCompleted + 1)
            .putInt("questionsAttempted", questionsAttempted + questions.size)
            .putInt("correctAnswers", correctAnswers + score)
            .putInt("lastScore", score)
            .putInt("lastTotal", questions.size)
            .apply()

        questionText.text = "🎉 Quiz Completed!"

        resultText.text =
            "Your Score: $score / ${questions.size}"

        resultText.visibility = TextView.VISIBLE

        optionA.visibility = TextView.GONE
        optionB.visibility = TextView.GONE
        optionC.visibility = TextView.GONE
        optionD.visibility = TextView.GONE

        previousButton.visibility = TextView.GONE
        nextButton.visibility = TextView.GONE
        progressText.text = "Quiz Finished"


    }
}