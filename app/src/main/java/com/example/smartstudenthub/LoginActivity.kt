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

            // Only empty fields are not allowed
            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter your ID and password",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Save the entered ID
                val sharedPreferences =
                    getSharedPreferences("SmartStudentHub", MODE_PRIVATE)

                sharedPreferences.edit()
                    .putString("username", enteredUsername)
                    .apply()

                // Open Home
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}