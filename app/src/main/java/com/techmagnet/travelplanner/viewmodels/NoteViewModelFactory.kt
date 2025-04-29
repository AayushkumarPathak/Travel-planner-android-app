package com.techmagnet.travelplanner.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techmagnet.travelplanner.repositories.NoteRepository

class NoteViewModelFactory(private  val repository: NoteRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(repository) as T;
    }

}