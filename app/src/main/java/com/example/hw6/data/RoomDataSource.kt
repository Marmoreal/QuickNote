package com.example.hw6.data

import com.example.hw6.App
import com.example.hw6.data.db.NoteDatabase
import com.example.hw6.data.db.NoteEntity
import com.example.hw6.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(
    private val database: NoteDatabase = App.instance.database
) {

    private val dao
        get() = database.dao()

    fun getNotes(): Flow<List<Note>> {
        return dao.getAll()
            .map { notes ->
                notes.map {
                    Note(it.id, it.text)
                }
            }
    }

    fun searchNotes(search: String): Flow<List<Note>> {
        return dao.searchNotes(search)
            .map { notes ->
                notes.map {
                    Note(it.id, it.text)
                }
            }
    }

    suspend fun putNote(note: Note) {
        dao.insertNote(
            NoteEntity(0, note.text)
        )
    }

    suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.id)
    }
}