package com.example.memorise.feature_note.domain.use_case.FolderUseCase

import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.repository.NoteRepository

class GetFolder(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Folder? {
        return repository.getFolderById(id)
    }
}