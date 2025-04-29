package com.techmagnet.travelplanner.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tripName: String,
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)