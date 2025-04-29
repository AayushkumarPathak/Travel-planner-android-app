package com.techmagnet.travelplanner.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.techmagnet.travelplanner.models.Trip
import com.techmagnet.travelplanner.repositories.TripRepository
import kotlinx.coroutines.launch

class TripViewModel(private val repository: TripRepository) : ViewModel() {

    fun getAllTrips(): LiveData<List<Trip>> = repository.allTrips

    fun insertTrip(trip: Trip) = viewModelScope.launch {
        repository.insert(trip)
    }

    fun updateTrip(trip: Trip) = viewModelScope.launch {
        repository.update(trip)
    }

    fun deleteTrip(trip: Trip) = viewModelScope.launch {
        repository.delete(trip)
    }

    fun refreshTrips(): LiveData<List<Trip>> = repository.allTrips
}
