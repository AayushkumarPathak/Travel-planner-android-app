package com.techmagnet.travelplanner.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.techmagnet.travelplanner.AddNoteActivity
import com.techmagnet.travelplanner.R
import com.techmagnet.travelplanner.adapters.NoteAdapter
import com.techmagnet.travelplanner.databinding.FragmentNotesBinding
import com.techmagnet.travelplanner.db.AppDatabase
import com.techmagnet.travelplanner.repositories.NoteRepository
import com.techmagnet.travelplanner.viewmodels.NoteViewModel
import com.techmagnet.travelplanner.viewmodels.NoteViewModelFactory

class NotesFragment : Fragment() {
    private lateinit var binding: FragmentNotesBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater, container, false)


        //Viewmodel + repository
        val dao = AppDatabase.getDatabase(requireContext()).noteDao()
        val repository = NoteRepository(dao)
        val factory = NoteViewModelFactory(repository)
        noteViewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

//        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(requireContext())

        // // Observe trips and set to adapter
        noteViewModel.getAllNotes().observe(viewLifecycleOwner){note ->
            binding.recyclerViewNotes.adapter = NoteAdapter(note){note ->
                noteViewModel.deleteNote(note);
            }
        }

        binding.fabAddNote.setOnClickListener{
            val intent = Intent(requireContext(),AddNoteActivity::class.java);
            startActivity(intent);
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume();
        noteViewModel.refreshNotes();
    }
}