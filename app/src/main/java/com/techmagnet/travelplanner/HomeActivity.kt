package com.techmagnet.travelplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.techmagnet.travelplanner.fragments.TripsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNav = findViewById(R.id.bottom_navigation)

        loadFragment(TripsFragment()) // Default fragment

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_trips -> loadFragment(TripsFragment())
//                R.id.nav_notes -> loadFragment(NoteFragment())
                R.id.nav_logout -> {
                    // Clear login status
                    val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    prefs.edit().putBoolean("isLoggedIn", false).apply()

                    Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, fragment)
            .commit()
    }
}
