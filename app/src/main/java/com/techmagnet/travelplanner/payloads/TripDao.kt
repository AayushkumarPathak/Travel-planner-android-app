package com.techmagnet.travelplanner.payloads

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techmagnet.travelplanner.models.Trip

@Dao
interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: Trip)

    @Query("SELECT * FROM trips ORDER BY startDate ASC")
    fun getAllTrips(): LiveData<List<Trip>>

    @Update
    suspend fun updateTrip(trip: Trip)

    @Delete
    suspend fun deleteTrip(trip: Trip)
}
