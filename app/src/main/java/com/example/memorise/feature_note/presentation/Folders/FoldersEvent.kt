package com.example.memorise.feature_note.presentation.Folders

import com.example.memorise.feature_note.domain.model.Folder

sealed class FoldersEvent {
    object ListFolder : FoldersEvent()
    data class DeleteFolder(val folder: Folder): FoldersEvent()
    object RestoreFolder: FoldersEvent()
}