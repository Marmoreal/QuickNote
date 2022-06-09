package com.example.hw6.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {
    @Query("Select * from notes order by id desc")
    fun getAll(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Query("Delete from notes where id in (:id)")
    suspend fun deleteNote(id: Int)

    @Query("Select * from notes where text like (:search)")
    fun searchNotes(search: String): Flow<List<NoteEntity>>
}