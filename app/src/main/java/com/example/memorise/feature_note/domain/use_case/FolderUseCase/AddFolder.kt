package com.example.memorise.feature_note.domain.use_case.FolderUseCase

import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.model.InvalidNoteException
import com.example.memorise.feature_note.domain.repository.NoteRepository

class AddFolder(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(folder: Folder) {
        if (folder.name.isBlank()) {
            throw InvalidNoteException("The title of the folder can't be empty")
        }
        repository.insertFolder(folder)
    }
}