package com.example.memorise.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorise.feature_note.domain.model.NoteType
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddEditNoteViewModel
    ) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Selection Screen")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            }
        ){
            selectionScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
@Composable
fun selectionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddEditNoteViewModel
    ) {
    var selectedNoteType by remember { mutableStateOf<NoteType?>(null) }

    Column(
        modifier = Modifier
            .padding(
                top = 72.dp
            )
            .fillMaxWidth()
    ) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                selectedNoteType = NoteType.BASIC
                navController.navigate(Screens.BasicNoteScreen.route)
            }) {
            Text(text = "Basic Note",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                selectedNoteType = NoteType.CORNELL
                navController.navigate(Screens.CornellNoteScreen.route)
            }) {
            Text(text = "Cornell Note Method",
                textAlign = TextAlign.Left)

        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                selectedNoteType = NoteType.OUTLINE
                navController.navigate(Screens.OutlineNoteScreen.route)
            }) {
            Text(text = "Outline Note Method",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                selectedNoteType = NoteType.QUADRANT
                navController.navigate(Screens.QuadrantNoteScreen.route)
            }) {
            Text(text = "Quadrant Note Method",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                selectedNoteType = NoteType.LADDER
                navController.navigate(Screens.LadderNoteScreen.route)
            }) {
            Text(text = "Ladder",
                textAlign = TextAlign.Left)
        }
//        Button(
//            modifier = modifier.fillMaxWidth(),
//            onClick = {
//                selectedNoteType = NoteType.IMAGE
//                navController.navigate(Screens.ImageNoteScreen.route)
//            }) {
//            Text(
//                text = "",
//                textAlign = TextAlign.Left,
//            )
//        }
    }
}