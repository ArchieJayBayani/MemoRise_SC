package com.example.memorise.feature_note.domain.use_case.CategoryUseCase

import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesListUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Category>> {
        return repository.getAllCategories()
    }
}