package com.example.hw6.domain

import com.example.hw6.data.NoteRepository
import com.example.hw6.data.NoteRepositoryImpl
import com.example.hw6.model.Note

class AddNoteUseCase(
    private val noteRepository: NoteRepository = NoteRepositoryImpl()
) {

    suspend operator fun invoke(text: String): Note {
        return noteRepository.addNote(text)
    }
}