package com.techmagnet.travelplanner

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.techmagnet.travelplanner.db.AppDatabase
import com.techmagnet.travelplanner.models.Note
import com.techmagnet.travelplanner.repositories.NoteRepository
import com.techmagnet.travelplanner.viewmodels.NoteViewModel
import com.techmagnet.travelplanner.viewmodels.NoteViewModelFactory

class AddNoteActivity : AppCompatActivity() {
    private lateinit var etTripName: EditText
    private lateinit var etTripDescription: EditText
    private lateinit var btnNote: Button
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize DAO, Repository, ViewModel
        val dao = AppDatabase.getDatabase(applicationContext).noteDao()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        // Bind views
        etTripName = findViewById(R.id.etTripName)
        etTripDescription = findViewById(R.id.etTripDescription)
        btnNote = findViewById(R.id.btnNote)

        // Save note
        btnNote.setOnClickListener {
            val title = etTripName.text.toString().trim()
            val description = etTripDescription.text.toString().trim()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                val note = Note(tripName = title, description = description)
                noteViewModel.insertNote(note)
//                Snackbar.make(root)
                Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}