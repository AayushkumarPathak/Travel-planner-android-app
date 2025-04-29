package com.techmagnet.travelplanner.repositories

import com.techmagnet.travelplanner.models.Note
import com.techmagnet.travelplanner.payloads.NoteDao

class NoteRepository(private val dao: NoteDao) {

    suspend fun insert(note: Note) = dao.insertNote(note)

    suspend fun delete(note: Note) = dao.deleteNote(note)

    fun getAllNotes() = dao.getAllNotes()
}