package com.example.hw6.domain

import com.example.hw6.data.NoteRepository
import com.example.hw6.data.NoteRepositoryImpl
import com.example.hw6.model.Note
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val noteRepository: NoteRepository = NoteRepositoryImpl()
) {

    operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNotes()
    }
}