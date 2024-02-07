package com.example.memorise.feature_note.presentation.Folders

import com.example.memorise.feature_note.domain.model.Folder

data class FolderState(
    val folder: List<Folder> = emptyList(),
    val recentDeletedFolder: Folder? = null
)
