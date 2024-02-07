package com.example.memorise.feature_note.domain.use_case.NotesUseCase

import com.example.memorise.feature_note.domain.repository.NoteRepository

class GetCategoryTitleForNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke (noteId : Int): String? {
        val categoryId = repository.getNoteById(noteId)?.category_id ?: return null
        return repository.getTitleForId(categoryId)
    }
}