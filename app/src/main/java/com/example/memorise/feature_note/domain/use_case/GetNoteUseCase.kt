package com.example.memorise.feature_note.domain.use_case

import com.example.memorise.feature_note.domain.model.UnifiedNote
import com.example.memorise.feature_note.domain.repository.NoteRepository

//1:38:00
class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): UnifiedNote? {
        return repository.getNoteById(id)
    }
}