package com.bilalmirza.noteit.repository

import androidx.lifecycle.LiveData
import com.bilalmirza.noteit.db.NoteDao
import com.bilalmirza.noteit.model.Note

class NoteRepository(
    private val noteDao: NoteDao
) {
    val readAllNotes: LiveData<List<Note>> = noteDao.readAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNode(note: Note) {
        noteDao.deleteNode(note)
    }
}