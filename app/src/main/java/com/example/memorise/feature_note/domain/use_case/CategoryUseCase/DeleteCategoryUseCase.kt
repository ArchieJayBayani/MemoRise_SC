package com.example.memorise.feature_note.domain.use_case.CategoryUseCase

import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.repository.NoteRepository

class DeleteCategoryUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(category: Category){
        repository.deleteCategory(category)
    }
}