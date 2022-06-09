package com.example.hw6.domain

import com.example.hw6.data.NoteRepositoryImpl
import com.example.hw6.model.Note

class DelNotesUseCase(
    private val noteRepository: NoteRepositoryImpl = NoteRepositoryImpl()
) {

    suspend operator fun invoke(note: Note){
        noteRepository.deleteNote(note)
    }
}