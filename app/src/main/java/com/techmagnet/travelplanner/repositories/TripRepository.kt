package com.techmagnet.travelplanner.repositories

import com.techmagnet.travelplanner.payloads.TripDao
import com.techmagnet.travelplanner.models.Trip

class TripRepository(private val dao: TripDao) {
    val allTrips = dao.getAllTrips()

    suspend fun insert(trip: Trip) = dao.insertTrip(trip)
    suspend fun update(trip: Trip) = dao.updateTrip(trip)
    suspend fun delete(trip: Trip) = dao.deleteTrip(trip)
}
