package com.example.loginscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.loginscreen.MainActivity
import com.example.loginscreen.R

class WelcomeActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Initialize views
        welcomeText = findViewById(R.id.welcomeText)
        logoutButton = findViewById(R.id.logoutButton)

        // Get account name from intent
        val accountName = intent.getStringExtra("ACCOUNT_NAME") ?: "User"

        // Set welcome message
        welcomeText.text = "Welcome $accountName!"

        // Set logout button listener
        logoutButton.setOnClickListener {
            // Navigate back to login screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}