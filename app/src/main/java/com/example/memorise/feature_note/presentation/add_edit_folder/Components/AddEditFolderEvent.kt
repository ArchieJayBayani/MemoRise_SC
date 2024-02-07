package com.example.memorise.feature_note.presentation.add_edit_folder.Components

sealed class AddEditFolderEvent {
    data class EnteredTitle(val value: String): AddEditFolderEvent()
    object SaveFolder : AddEditFolderEvent()
}