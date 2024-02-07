package com.example.memorise.feature_note.presentation.category

import com.example.memorise.feature_note.domain.model.Category

data class CategoryState(
    val categories: List<Category> = emptyList()
)
