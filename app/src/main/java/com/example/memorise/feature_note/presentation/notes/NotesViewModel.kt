package com.example.memorise.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.NoteUseCases
import com.example.memorise.feature_note.domain.util.NoteOrder
import com.example.memorise.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(NotesState())
    val state: Flow<NotesState> = _state.asStateFlow()

    private var recentlyDeletedNote: Note? = null
    private var getNotesJob : Job? = null
    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }


    private suspend operator  fun invoke(noteId: Int): String {
        return noteUseCases.getCategoryTitleForNote(noteId) ?: ""
    }

    //this is used to retrieve the category title from the category entity and display it in the note item composable
    fun getCategoryTitleForNoteAsync(noteId: Int): Flow<String> {
        return flow {
            emit(invoke(noteId))
        }
    }

    fun getNotesByFolderId(folderId: Int?, defaultNoteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)): Flow<List<Note>> {
        return noteUseCases.getNotesByFolderId(folderId)
            .onEach { notes ->
                _state.value = _state.value.copy(
                    notes = notes,
                    noteOrder = defaultNoteOrder,
                    searchQuery = null
                )
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), _state.value.notes)
    }



    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if(_state.value.noteOrder::class == event.noteOrder::class &&
                    _state.value.noteOrder.orderType == event.noteOrder.orderType
                    ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    val note = recentlyDeletedNote ?: return@launch
                    noteUseCases.addNote(note, note.category_id, note.folderId)
                    recentlyDeletedNote = null
                }
            }
//            is NotesEvent.RestoreNote -> {
//                viewModelScope.launch {
//                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch, recentlyDeletedNote.category_id)
//                    recentlyDeletedNote = null
//                }
//            }
            is NotesEvent.Search -> {
                getNotesWithQuery(event.query)
            }
        }
    }
    private fun updateNotesWithQuery(newQuery: String, notes: List<Note>) {
        _state.value = _state.value.copy(
            notes = notes,
            searchQuery = if (newQuery != _state.value.searchQuery) newQuery else null
        )
    }
    private fun getNotesWithQuery(query: String) {
        getNotesJob?.cancel()
        getNotesJob = flow {
            emit(noteUseCases.searchNotes.execute(query))
        }.debounce(300)
            .onEach { notesFlow ->
                notesFlow.collect { notes ->
                    updateNotesWithQuery(query, notes)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = _state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder,
                    searchQuery = null
                )
            }
            .launchIn(viewModelScope)
    }
}