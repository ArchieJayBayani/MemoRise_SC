//package com.example.memorise.feature_note.presentation.notes
//
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.memorise.feature_note.domain.model.Note
//import com.example.memorise.feature_note.domain.model.NoteType
//import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
//import com.example.memorise.feature_note.presentation.notes.components.NoteItem
//
//@Composable
//fun NotesList(
//    navController: NavController,
//    notes: List<Note>,
//    onNoteClick: (Note) -> Unit,
//    onDeleteClick: (Note) -> Unit,
//    notesViewModel: NotesViewModel = hiltViewModel()
//) {
//    LazyColumn {
//        items(notes) { note ->
//            val categoryTitle by notesViewModel.getCategoryTitleForNoteAsync(note.id ?: 0).collectAsState("")
//
//            NoteItem(
//                note = note,
//                category = categoryTitle,
//                modifier = Modifier
//                    .fillMaxWidth(),
//                onItemClick = onNoteClick,
//                onDeleteClick = {
//                    notesViewModel.onEvent(NotesEvent.DeleteNote(note))
//                }
//            )
//        }
//    }
//}
