package com.example.memorise.feature_note.presentation.notes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.memorise.feature_note.domain.model.NoteType
import com.example.memorise.feature_note.presentation.Folders.FolderItem
import com.example.memorise.feature_note.presentation.Folders.FolderState
import com.example.memorise.feature_note.presentation.Folders.FolderViewModel
import com.example.memorise.feature_note.presentation.Folders.FoldersEvent
import com.example.memorise.feature_note.presentation.Folders.ListBackStackItem
import com.example.memorise.feature_note.presentation.ScreenNavigations.NavigationItem
import com.example.memorise.feature_note.presentation.ScreenNavigations.getNavigationItems
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
import com.example.memorise.feature_note.presentation.notes.components.NoteItem
import com.example.memorise.feature_note.presentation.notes.components.OrderSection
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    items: List<NavigationItem>,
    notesViewModel: NotesViewModel = hiltViewModel(),
    foldersViewModel: FolderViewModel = hiltViewModel(),
) {
    var selectedFolderId by rememberSaveable { mutableStateOf<Int?>(null) }

    val folderStates by foldersViewModel.folderState.collectAsState(initial = FolderState())
    val notesState by notesViewModel.state.collectAsState(initial = NotesState())
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val items = getNavigationItems(navController = navController)
    val searchQueryState = remember { mutableStateOf(TextFieldValue()) }

    //added for snackbar:
    LaunchedEffect(key1 = true) {
        notesViewModel.state.collect { state: NotesState ->
            if (state.recentlyDeletedNote != null) {
                val result = scaffoldState.snackbarHostState.showSnackbar(
                    message = "Note Deleted",
                    actionLabel = "Undo"
                )
                if (result == SnackbarResult.ActionPerformed) {
                    notesViewModel.onEvent(NotesEvent.RestoreNote)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        foldersViewModel.onFolderEvent(FoldersEvent.ListFolder)
    }

    LaunchedEffect(Unit) {
        notesViewModel.getNotesByFolderId(null)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
        var showButtonList by remember {
            mutableStateOf(false)
        }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "MemoRise",
                        modifier = Modifier
                            .padding(
                                start = 60.dp,
                                bottom = 20.dp
                            ),
                        fontSize = 20.sp
                    )
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                item.route(navController)
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                modifier = Modifier,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "MemoRise")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Burger Menu"
                                )
                            }
                        },
                        actions = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.width(52.dp))
                                TextField(
                                    value = searchQueryState.value,
                                    onValueChange = { newValue: TextFieldValue ->
                                        searchQueryState.value = newValue
                                        notesViewModel.onEvent(NotesEvent.Search(newValue.text))
                                    },
                                    label = { Text("Search Notes") },
                                    modifier = Modifier
                                        .weight(1f)
//                                        .padding(top = 32.dp, bottom = 32.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                )
                                Box(
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                ) {
                                    OrderSection(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        noteOrder = notesState.noteOrder,
                                        onOrderChange = {
                                            notesViewModel.onEvent(NotesEvent.Order(it))
                                        }
                                    )
                                }
                            }
                        }
                    )
                },
            ) { values ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
                ) {
                    if (selectedFolderId != null) {
                        item {
                            ListBackStackItem(
                                onItemClick = { selectedFolderId = null }
                            )
                        }
                    }

                    if (selectedFolderId == null) {
                        items(folderStates.folder) { folder ->
                            FolderItem(
                                navController = navController,
                                folder = folder,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onItemClick = {
                                    selectedFolderId = folder.id
                                },
                                onDeleteClick = {
                                    foldersViewModel.onFolderEvent(FoldersEvent.DeleteFolder(folder))
                                }
                            )
                        }
                    }

//this works on filtering notes:
//                    items(notesState.notes.filter { it.folderId == selectedFolderId }) {

                    items(if (selectedFolderId != null) {
                        notesState.notes.filter { it.folderId == selectedFolderId }
                    } else {
                        notesState.notes.filter { it.folderId == -1 }
                    }) { note ->
                        val categoryTitle by notesViewModel.getCategoryTitleForNoteAsync(
                            note.id ?: 0
                        ).collectAsState("")
                        NoteItem(
                            note = note,
                            category = categoryTitle,
                            modifier = Modifier
                                .fillMaxWidth(),
                            onItemClick = {
                                val route = when (note.noteType) {
                                    NoteType.BASIC -> Screens.BasicNoteScreen.route + "?noteId=${note.id}"
                                    NoteType.CORNELL -> Screens.CornellNoteScreen.route + "?noteId=${note.id}"
                                    NoteType.QUADRANT -> Screens.QuadrantNoteScreen.route + "?noteId=${note.id}"
                                    NoteType.OUTLINE -> Screens.OutlineNoteScreen.route + "?noteId=${note.id}"
                                    NoteType.LADDER -> Screens.LadderNoteScreen.route + "?noteId=${note.id}"
                                    NoteType.IMAGE -> Screens.ImageNoteScreen.route + "?noteId=${note.id}"
                                }
                                navController.navigate(route)
                            },
                            scaffoldState = scaffoldState,
                            onDeleteClick = {
                                notesViewModel.onEvent(NotesEvent.DeleteNote(note))
                            }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                IconButton(
                    onClick = { showButtonList = !showButtonList },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(40.dp)
                    )
                    DropdownMenu(
                        expanded = showButtonList,
                        onDismissRequest = { showButtonList = false },
                    ) {
                        DropdownMenuItem(
                            text = { Text("Add new note") },
                            onClick = { navController.navigate(Screens.NoteSelectionScreen.route) }
                        )
                        DropdownMenuItem(
                            text = { Text("Add new image") },
                            onClick = {
                                navController.navigate(Screens.ImageNoteScreen.route)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Add new folder") },
                            onClick = {
                                navController.navigate(Screens.FolderScreen.route)
                            }
                        )
                    }
                }
            }
        }
    }
}

