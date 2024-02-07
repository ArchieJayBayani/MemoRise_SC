package com.example.memorise.feature_note.domain.use_case.CategoryUseCase

import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.InvalidNoteException
import com.example.memorise.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddCategory(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(category: Category) {
        if (category.categoryTitle.isBlank()) {
            throw InvalidNoteException("The title of the category can't be empty")
        }
        repository.insertCategory(category)
    }
}