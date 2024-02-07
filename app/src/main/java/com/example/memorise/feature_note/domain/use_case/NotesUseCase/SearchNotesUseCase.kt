package com.example.memorise.feature_note.domain.use_case.NotesUseCase

import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class SearchNotesUseCase(
    private val repository: NoteRepository
) {
    fun execute(query: String): Flow<List<Note>> {
        return repository.searchNotes(query)
    }
}