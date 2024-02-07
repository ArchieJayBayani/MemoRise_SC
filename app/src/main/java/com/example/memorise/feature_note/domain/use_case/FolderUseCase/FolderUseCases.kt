package com.example.memorise.feature_note.domain.use_case.FolderUseCase

data class FolderUseCases(
    val getFolderList: GetFolderList,
    val getFolder: GetFolder,
    val deleteFolder: DeleteFolder,
    val addFolder: AddFolder,
)
