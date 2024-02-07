package com.example.memorise.feature_note.domain.use_case.NotesUseCase

import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesByFolderIdUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke (folderId: Int?): Flow<List<Note>> {
        return noteRepository.getNotesByFolderId(folderId)
    }
}