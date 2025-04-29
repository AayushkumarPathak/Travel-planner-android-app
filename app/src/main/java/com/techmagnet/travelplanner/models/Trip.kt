package com.techmagnet.travelplanner.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "trips")
data class Trip (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val location: String,
    val startDate: String,
    val endDate: String
)