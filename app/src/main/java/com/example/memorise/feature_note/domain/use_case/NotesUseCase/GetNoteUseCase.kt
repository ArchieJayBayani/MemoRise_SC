package com.example.memorise.feature_note.domain.use_case.NotesUseCase

import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.repository.NoteRepository

//1:38:00
class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}