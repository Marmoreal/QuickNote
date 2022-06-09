package com.example.hw6.domain

import androidx.lifecycle.LiveData
import com.example.hw6.data.NoteRepository
import com.example.hw6.data.NoteRepositoryImpl
import com.example.hw6.model.Note
import kotlinx.coroutines.flow.Flow

class SearchNotesUseCase(
    private val noteRepository: NoteRepository = NoteRepositoryImpl()
) {

    operator fun invoke(search: String): Flow<List<Note>> {
        return noteRepository.searchNotes(search)
    }
}