package com.techmagnet.travelplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techmagnet.travelplanner.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Login button click
        /**
         * @return Local storage is used to Authenticate existing user
         * */
        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            val userData = getSharedPreferences("UserPrefs", MODE_PRIVATE)

            val storedUsername = userData.getString("username", null)
            val storedPassword = userData.getString("password", null)

            Toast.makeText(this, "Stored: $storedUsername - $storedPassword", Toast.LENGTH_LONG).show()

            if (username == storedUsername && password == storedPassword) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to next screen
                 startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}