package com.example.memorise.ui.screens.noteScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.memorise.R
import com.example.memorise.feature_note.domain.model.NoteType
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteEvent
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteViewModel
import com.example.memorise.ui.screens.Bottomappbar
import com.example.memorise.ui.screens.Topappbar
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LadderNote(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
) {
    val categories = rememberCategoriesState(viewModel)
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Topappbar (
        navController = navController,
        name = "Ladder Note",
        showCategoryDropdown = true,
        categories = categories.value,
        selectedCategory = selectedCategory,
        onCategorySelected = { category ->
            viewModel.onCategorySelected(category)
        }
    ) {
        viewModel.onNoteTypeSelected(NoteType.LADDER)
        ladderNote(navController, viewModel)
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ladderNote(
    navController: NavController,
    viewModel: AddEditNoteViewModel,
    modifier: Modifier = Modifier,
) {
    val folder = rememberFoldersState(viewModel)
    val selectedFolder by viewModel.selectedFolder.collectAsState()

    val titleState = viewModel.noteTitle.value
    val content1State = viewModel.noteContent1.value
    val content2State = viewModel.noteContent2.value
    val content3State = viewModel.noteContent3.value
    val content4State = viewModel.noteContent4.value
    val content5State = viewModel.noteContent5.value

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditNoteViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditNoteViewModel.UiEvent.SaveNote -> {
                    navController.navigate(Screens.MainScreen.route)
                }
            }
        }
    }

    Bottomappbar(
        navController = navController,
        showFolderDropdown = true,
        folders = folder.value,
        selectedFolder = selectedFolder,
        showTextFormattingButton = false,
        onToggleBold = {viewModel.toggleBold()},
        onToggleItalic = {viewModel.toggleItalic()},
        onToggleUnderline = { viewModel.toggleUnderline() },
        onFolderSelected = { folder ->
            viewModel.onFolderSelected(folder)
        },
        content = {
            paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .horizontalScroll(rememberScrollState())
                    .padding(
                        start = 8.dp,
                        top = 72.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    )
                    .padding(paddingValues)
            ) {
                TextField(
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    label = {Text(text = "Title")},
                    value = titleState.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                    singleLine = true
                )
                TextField(
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .width(110.dp),
                    label = {Text(text = "Content")},
                    value = content1State.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent1(it))
                    },
                )
                TextField(
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .width(165.dp),
                    label = {Text(text = "Content")},
                    value = content2State.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent2(it))
                    },

                    )
                TextField(
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .width(220.dp),
                    label = {Text(text = "Content")},
                    value = content3State.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent3(it))
                    },
                )
                TextField(
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .width(275.dp),
                    label = {Text(text = "Content")},
                    value = content4State.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent4(it))
                    },
                )
                TextField(
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                        .width(325.dp),
                    label = {Text(text = "Content")},
                    value = content5State.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent5(it))
                    },
                )
            }
        }
    )
}
