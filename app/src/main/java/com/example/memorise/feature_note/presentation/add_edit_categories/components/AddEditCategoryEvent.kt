package com.example.memorise.feature_note.presentation.add_edit_categories.components

sealed class AddEditCategoryEvent{
    data class EnteredTitle(val value: String) : AddEditCategoryEvent()
    object SaveCategory : AddEditCategoryEvent()
}
