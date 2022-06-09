package com.example.hw6.data

import androidx.lifecycle.LiveData
import com.example.hw6.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(text: String): Note
    suspend fun deleteNote(note: Note)
    fun searchNotes(search: String): Flow<List<Note>>
}