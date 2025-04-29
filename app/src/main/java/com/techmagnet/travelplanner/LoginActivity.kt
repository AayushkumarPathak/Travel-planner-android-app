package com.techmagnet.travelplanner

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techmagnet.travelplanner.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userData = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        // If user is already logged in, go directly to onboarding screen
        if (userData.getBoolean("isLoggedIn", false)) {
            startActivity(Intent(this, OnboardingScreen::class.java))
            finish()
        }

        // Change text color when typing
        setTextColorOnTyping(binding.username)
        setTextColorOnTyping(binding.password)

        // Login button click
        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            val storedUsername = userData.getString("username", null)
            val storedPassword = userData.getString("password", null)

            if (username == storedUsername && password == storedPassword) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                // Save login status
                val editor = userData.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                // Navigate to onboarding screen
                startActivity(Intent(this, OnboardingScreen::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigate to Register screen
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    // Function to change text color when typing
    private fun setTextColorOnTyping(editText: android.widget.EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editText.setTextColor(Color.BLACK)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
