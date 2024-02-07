package com.example.memorise.feature_note.domain.use_case.NotesUseCase

data class NoteUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNote,
    val getNote: GetNoteUseCase,
    val searchNotes: SearchNotesUseCase,
    val getCategoryTitleForNote: GetCategoryTitleForNoteUseCase,
    val getNotesByFolderId: GetNotesByFolderIdUseCase
)
