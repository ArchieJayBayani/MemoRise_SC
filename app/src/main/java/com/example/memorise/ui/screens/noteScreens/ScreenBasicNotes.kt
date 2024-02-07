package com.example.memorise.ui.screens.noteScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.memorise.R
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.NoteType
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteEvent
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteViewModel
import com.example.memorise.ui.screens.Topappbar
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.model.FormattedSegment
import com.example.memorise.feature_note.presentation.add_edit_notes.components.CategoryDropdown
import com.example.memorise.feature_note.presentation.add_edit_notes.components.FolderDropdown
import com.example.memorise.ui.screens.Bottomappbar

@Composable
fun rememberCategoriesState(viewModel: AddEditNoteViewModel): State<List<Category>> {
    return viewModel.categories.collectAsState(initial = emptyList())
}

@Composable
fun rememberFoldersState(viewModel: AddEditNoteViewModel): State<List<Folder>> {
    return viewModel.folders.collectAsState(initial = emptyList())
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun basicNote(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    ) {
    val categories = rememberCategoriesState(viewModel)
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Topappbar (
        navController = navController,
        name = "Basic Note",
        showCategoryDropdown = true,
        categories = categories.value,
        selectedCategory = selectedCategory,
        onCategorySelected = { category ->
            viewModel.onCategorySelected(category)
        }
    ) {
        viewModel.onNoteTypeSelected(NoteType.BASIC)
        basicTextFields(navController, viewModel)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun basicTextFields(
    navController: NavController ,
    viewModel: AddEditNoteViewModel,
    modifier: Modifier = Modifier,
) {
    val folder = rememberFoldersState(viewModel)
    val selectedFolder by viewModel.selectedFolder.collectAsState()

    val titleState = viewModel.noteTitle.value
    val content1State = viewModel.noteContent1.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
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
        onFolderSelected = { folder ->
            viewModel.onFolderSelected(folder)
        },
        showTextFormattingButton = false,
        onToggleBold = {viewModel.toggleBold()},
        onToggleItalic = {viewModel.toggleItalic()},
        onToggleUnderline = { viewModel.toggleUnderline() },
        content = {
            paddingValues ->
            Column(
                modifier = Modifier
                    .padding(top = 72.dp)
                    .padding(paddingValues)
                    .fillMaxWidth()
            ) {
                TextField(
                    label = { Text(text = "Title") },
                    value = titleState.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                    },
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                        )
                        .clip(RoundedCornerShape(12.dp))
                )
                TextField(
                    label = { Text(text = "Content") },
                    value = content1State.text,
                    onValueChange = {
                        viewModel.onEvent(AddEditNoteEvent.EnteredContent1(it))
                    },
                    textStyle = TextStyle(
                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
                    ),
                    modifier = modifier
                        .fillMaxSize()
                        .padding(
                            top = 8.dp,
                            start = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        )
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    )
}

//                TextField(
//                    label = { Text(text = "Title") },
//                    value = titleState.text,
//                    onValueChange = {
//                        viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
//                    },
//                    textStyle = TextStyle(
//                        fontWeight = if (viewModel.isBold.value) FontWeight.Bold else null,
//                        fontStyle = if (viewModel.isItalic.value) FontStyle.Italic else null,
//                        textDecoration = if (viewModel.isUnderlined.value) TextDecoration.Underline else null
//                    ),
//                    singleLine = true,
//                    modifier = modifier
//                        .fillMaxWidth()
//                        .padding(
//                            start = 8.dp,
//                            end = 8.dp,
//                        )
//                        .clip(RoundedCornerShape(12.dp))
//                )
