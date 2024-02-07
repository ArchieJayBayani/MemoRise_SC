package com.example.memorise.feature_note.presentation.category

import com.example.memorise.feature_note.domain.model.Category

sealed class CategoriesEvent {
    object ListCategory : CategoriesEvent()
    data class DeleteCategory(val category: Category): CategoriesEvent()
    object RestoreCategory: CategoriesEvent()
}
