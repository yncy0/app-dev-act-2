package com.example.loginscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginscreen.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val accounts = mapOf(
        "john_doe" to Pair("password123", "John Doe"),
        "jane_smith" to Pair("securepass456", "Jane Smith"),
        "bob_johnson" to Pair("mypass789", "Bob Johnson")
    )

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)

        // Set login button click listener
        loginButton.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val username = usernameInput.text.toString().trim()
        val password = passwordInput.text.toString()

        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            return
        }

        // Check credentials
        if (accounts.containsKey(username)) {
            val (correctPassword, fullName) = accounts[username]!!
            if (password == correctPassword) {
                // Successful login
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

                // Navigate to WelcomeActivity
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("ACCOUNT_NAME", fullName)
                startActivity(intent)
                finish()
            } else {
                // Wrong password
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show()
                passwordInput.text.clear()
            }
        } else {
            // Username not found
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show()
            passwordInput.text.clear()
        }
    }
}