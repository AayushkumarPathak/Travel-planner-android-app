package com.techmagnet.travelplanner.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techmagnet.travelplanner.models.Note
import com.techmagnet.travelplanner.repositories.NoteRepository
import kotlinx.coroutines.launch


class NoteViewModel(private val repository: NoteRepository):ViewModel() {

        fun getAllNotes() : LiveData<List<Note>> = repository.getAllNotes();


        fun insertNote(note:Note) = viewModelScope.launch {
            repository.insert(note);
        }

        fun deleteNote(note:Note) = viewModelScope.launch {
            repository.delete(note);
        }

        fun refreshNotes(): LiveData<List<Note>> = repository.getAllNotes();

}