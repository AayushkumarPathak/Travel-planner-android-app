package com.techmagnet.travelplanner.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.techmagnet.travelplanner.models.Note
import com.techmagnet.travelplanner.models.Trip
import com.techmagnet.travelplanner.payloads.NoteDao
import com.techmagnet.travelplanner.payloads.TripDao

@Database(entities = [Trip::class,Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tripDao(): TripDao
    abstract fun noteDao(): NoteDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "trip_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
