package com.example.smartstudenthub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.usernameEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {

            val enteredUsername = username.text.toString().trim()
            val enteredPassword = password.text.toString()

            if (enteredUsername == "student" && enteredPassword == "1234") {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Invalid username or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}