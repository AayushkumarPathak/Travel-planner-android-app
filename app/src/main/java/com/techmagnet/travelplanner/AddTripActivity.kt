package com.techmagnet.travelplanner

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.techmagnet.travelplanner.db.AppDatabase
import com.techmagnet.travelplanner.models.Trip
import com.techmagnet.travelplanner.repositories.TripRepository
import com.techmagnet.travelplanner.viewmodels.TripViewModel
import com.techmagnet.travelplanner.viewmodels.TripViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class AddTripActivity : ComponentActivity() {

    private lateinit var etTripTitle: EditText
    private lateinit var etTripLocation: EditText
    private lateinit var etStartDate: EditText
    private lateinit var etEndDate: EditText
    private lateinit var btnSaveTrip: Button
    private lateinit var tripViewModel: TripViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trip)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize DAO, Repository, ViewModel
        val dao = AppDatabase.getDatabase(applicationContext).tripDao()
        val repository = TripRepository(dao)
        val factory = TripViewModelFactory(repository)
        tripViewModel = ViewModelProvider(this, factory)[TripViewModel::class.java]

        // Bind views
        etTripTitle = findViewById(R.id.etTripTitle)
        etTripLocation = findViewById(R.id.etTripLocation)
        etStartDate = findViewById(R.id.etStartDate)
        etEndDate = findViewById(R.id.etEndDate)
        btnSaveTrip = findViewById(R.id.btnSaveTrip)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Set up date pickers
        etStartDate.setOnClickListener {
            showDatePicker { date -> etStartDate.setText(dateFormat.format(date)) }
        }

        etEndDate.setOnClickListener {
            showDatePicker { date -> etEndDate.setText(dateFormat.format(date)) }
        }

        btnSaveTrip.setOnClickListener {
            val title = etTripTitle.text.toString().trim()
            val location = etTripLocation.text.toString().trim()
            val startDate = etStartDate.text.toString().trim()
            val endDate = etEndDate.text.toString().trim()

            if (title.isNotEmpty() && location.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty()) {
                val trip = Trip(title = title, location = location, startDate = startDate, endDate = endDate)
                tripViewModel.insertTrip(trip)
                Toast.makeText(this, "Trip Saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePicker(onDateSelected: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}
