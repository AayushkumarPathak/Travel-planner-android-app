//package com.techmagnet.travelplanner
//
//import android.content.Intent
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class RegisterActivity : AppCompatActivity() {
//
//private lateinit var sharedPrefs: SharedPreferences
//    private lateinit var username: EditText
//    private lateinit var password: EditText
//    private lateinit var edtName: EditText
//    private lateinit var email: EditText
//    private lateinit var btnRegister: Button
//    private lateinit var tvAlreadyAccount: TextView
//    /**
//     * @return Local storage is used to store user
//     * */
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        // init views
//        username = findViewById(R.id.username)
//        password = findViewById(R.id.password)
//        edtName = findViewById(R.id.Name)
//        email = findViewById(R.id.email)
//        btnRegister = findViewById(R.id.btnRegister)
//        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount)
//
//        // init shared prefs
//        sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
//
//        btnRegister.setOnClickListener {
//            val name = edtName.text.toString()
//            val email = email.text.toString()
//            val username = username.text.toString()
//            val password = password.text.toString()
//
//            if (name.isBlank() || email.isBlank() || username.isBlank() || password.isBlank()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//            } else {
//                // Save to SharedPreferences
//                val editor = sharedPrefs.edit()
//                editor.putString("username", username)
//                editor.putString("password", password)
//                editor.putString("name", name)
//                editor.putString("email", email)
//                editor.apply()
//
//                Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()
//
//                // Redirect to login
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//
//        tvAlreadyAccount.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
//}
package com.techmagnet.travelplanner

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var edtName: EditText
    private lateinit var email: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvAlreadyAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        edtName = findViewById(R.id.Name)
        email = findViewById(R.id.email)
        btnRegister = findViewById(R.id.btnRegister)
        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount)

        // Initialize SharedPreferences
        sharedPrefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val nameText = edtName.text.toString().trim()
            val emailText = email.text.toString().trim()
            val usernameText = username.text.toString().trim()
            val passwordText = password.text.toString().trim()

            if (nameText.isBlank() || emailText.isBlank() || usernameText.isBlank() || passwordText.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save credentials to SharedPreferences
                sharedPrefs.edit().apply {
                    putString("username", usernameText)
                    putString("password", passwordText)
                    putString("name", nameText)
                    putString("email", emailText)
                    apply()
                }

                // Log for debugging
                val savedUsername = sharedPrefs.getString("username", null)
                val savedPassword = sharedPrefs.getString("password", null)
                val savedName = sharedPrefs.getString("name", null)
                val savedEmail = sharedPrefs.getString("email", null)

                Log.d("SharedPrefsDebug", "Username: $savedUsername")
                Log.d("SharedPrefsDebug", "Password: $savedPassword")
                Log.d("SharedPrefsDebug", "Name: $savedName")
                Log.d("SharedPrefsDebug", "Email: $savedEmail")

                // Toast to confirm save
                Toast.makeText(
                    this,
                    "Saved: $savedUsername | $savedEmail",
                    Toast.LENGTH_LONG
                ).show()

                // Navigate to LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        tvAlreadyAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

