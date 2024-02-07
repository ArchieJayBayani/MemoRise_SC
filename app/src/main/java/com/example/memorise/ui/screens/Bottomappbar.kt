package com.example.memorise.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.memorise.R
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteEvent
import com.example.memorise.feature_note.presentation.add_edit_notes.AddEditNoteViewModel
import com.example.memorise.feature_note.presentation.add_edit_notes.components.CategoryDropdown
import com.example.memorise.feature_note.presentation.add_edit_notes.components.FolderDropdown
import com.example.memorise.ui.screens.noteScreens.basicNote
import com.example.memorise.ui.screens.noteScreens.basicTextFields

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bottomappbar(
    navController: NavController,
    showFolderDropdown: Boolean = false,
    folders: List<Folder> = emptyList(),
    selectedFolder: Folder? = null,
    onFolderSelected: (Folder) -> Unit = {},
    showTextFormattingButton: Boolean = false,
    onToggleBold: () -> Unit,
    onToggleItalic: () -> Unit,
    onToggleUnderline: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
    viewModel: AddEditNoteViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier,
            bottomBar = {
                androidx.compose.material3.BottomAppBar(
                    actions = {
                        if (showTextFormattingButton) {
                            IconButton(onClick = {
                                onToggleBold
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.format_bold_fill0_wght400_grad0_opsz24),
                                    contentDescription = "Bold"
                                )
                            }
                            IconButton(onClick = {
                                onToggleItalic
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.format_italic_fill0_wght400_grad0_opsz24),
                                    contentDescription = "Italic"
                                )
                            }
                            IconButton(onClick = {
                                onToggleUnderline
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.format_underlined_fill0_wght400_grad0_opsz24),
                                    contentDescription = "Underline"
                                )
                            }
                        }
                        FolderDropdown(
                            folders = folders,
                            selectedFolder = selectedFolder,
                            onFolderSelected = onFolderSelected
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { viewModel.onEvent(AddEditNoteEvent.SaveNote) }) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Save Note",
                                modifier = Modifier
                            )
                        }
                    }
                )
            },
            content = content
        )
    }
}