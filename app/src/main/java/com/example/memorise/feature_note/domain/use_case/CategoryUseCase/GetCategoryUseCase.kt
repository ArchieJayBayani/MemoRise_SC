package com.example.memorise.feature_note.domain.use_case.CategoryUseCase

import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.UnifiedNote
import com.example.memorise.feature_note.domain.repository.NoteRepository

class GetCategoryUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Category? {
        return repository.getCategoryById(id)
    }
}