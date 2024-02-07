package com.example.memorise.feature_note.presentation.notes

import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    data class Search(val query: String): NotesEvent()
}
