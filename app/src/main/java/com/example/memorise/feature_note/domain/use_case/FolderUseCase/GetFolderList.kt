package com.example.memorise.feature_note.domain.use_case.FolderUseCase

import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetFolderList(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Folder>> {
        return repository.getAllFolders()
    }
}