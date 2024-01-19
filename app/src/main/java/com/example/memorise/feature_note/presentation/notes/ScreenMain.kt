package com.example.memorise.feature_note.presentation.notes

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.memorise.feature_note.domain.model.UnifiedNote
import com.example.memorise.feature_note.presentation.notes.components.NavigationItem
import com.example.memorise.feature_note.presentation.notes.components.getNavigationItems
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
import com.example.memorise.feature_note.presentation.notes.components.NoteItem
import com.example.memorise.feature_note.presentation.notes.components.OrderSection
import kotlinx.coroutines.launch





@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    items: List<NavigationItem>,
    viewModel: NotesViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val items = getNavigationItems(navController = navController)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
//        var showMoreVert by remember {
//            mutableStateOf(false)
//        }
        var showButtonList by remember {
            mutableStateOf(false)
        }
        var AddNewNotes by remember {
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
//                scaffoldState = scaffoldState,
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
                            IconButton(
                                onClick = { /*TODO*/ }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search"
                                )
                            }
                            OrderSection(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                noteOrder = state.noteOrder,
                                onOrderChange = {
                                    viewModel.onEvent(NotesEvent.Order(it))
                                }
                            )
                        }
                    )
                },
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.notes) { note ->
                        NoteItem(
                            note = note,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                },
                            onDeleteClick = {
                                viewModel.onEvent(NotesEvent.DeleteNote(note))
                                scope.launch {
                                    val result = scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Note Deleted",
                                        actionLabel = "Undo"
                                    )
                                    if(result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(NotesEvent.RestoreNote)
                                    }
                                }
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
                            onClick = { /*TODO*/ }
                        )
                        DropdownMenuItem(
                            text = { Text("Add new folder") },
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            }
        }
    }
}




//this function gets a list for the vertical menu within the main screen, the three dots with the sort by, view by, and categories
//@Composable
//fun homeMoreVert(
//
//) {
//    var showMoreVert by remember {
//        mutableStateOf(false)
//    }
//    IconButton(onClick = { showMoreVert = !showMoreVert }) {
//        Icon(
//            imageVector = Icons.Default.MoreVert,
//            contentDescription = "More Options"
//        )
//    }
//    DropdownMenu(
//        expanded = showMoreVert,
//        onDismissRequest = { showMoreVert = false }) {
//        DropdownMenuItem(
//            text = { Text("Sort by") },
//            onClick = {}
//        )
////            onClick = {})
//        DropdownMenuItem(
//            text = { Text("View by") },
//            onClick = {}
//        )
//        DropdownMenuItem(
//            text = { Text("Categories") },
//            onClick = {}
//        )
//    }
//}

//@Composable
//fun addButton (
//) {
//    var showButtonList by remember {
//        mutableStateOf(false)
//    }
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp)
//    ) {
//        IconButton(onClick = {showButtonList = !showButtonList},
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .size(60.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "Add",
//                modifier = Modifier
//                    .size(40.dp)
//            )
//            DropdownMenu(
//                expanded = showButtonList,
//                onDismissRequest = {showButtonList = false},
//            ) {
//                DropdownMenuItem(
//                    text = { Text("Add new note")},
//                    onClick = {}
//                )
//                addNewNote()
//                DropdownMenuItem(
//                    text = { Text("Add new image") },
//                    onClick = { /*TODO*/ }
//                )
//                DropdownMenuItem(
//                    text = { Text("Add new folder") },
//                    onClick = { /*TODO*/ }
//                )
//            }
//        }
//    }
//}

//this functions list all the note taking methods after selecting "Add new note" from the + selection in main screen
//@Composable
//fun addNewNote(
//    navigateToBasicNote: () -> Unit,
//    navigateToCornellNote: () -> Unit,
//    navigateToOutlineNote: () -> Unit,
//    navigateToChartingNote: () -> Unit,
//    navigateToQuadrantNote: () -> Unit,
//
//) {
//    var AddNewNotes by remember{
//        mutableStateOf(false)
//    }
//    DropdownMenuItem(
//        text = { Text("Add new note") },
//        onClick = {AddNewNotes = !AddNewNotes}
//    )
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        ,
//    ) {
//        DropdownMenu(
//        expanded = AddNewNotes,
//        onDismissRequest = {AddNewNotes = false},
//        )
//    {
//        DropdownMenuItem(
//            text = { Text("Basic Note") },
//            onClick = {
////               navController.navigate(Screens.BasicNoteScreen.route)
//            }
//        )
//        DropdownMenuItem(
//            text = { Text("Cornell Note Method") },
//            onClick = {
////                navController.navigate(Screens.CornellNoteScreen.route)
//            }
//        )
//        DropdownMenuItem(
//            text = { Text("Outline Note Method") },
//            onClick = {}
//        )
//        DropdownMenuItem(
//            text = { Text("Charting Note Method") },
//            onClick = {}
//        )
//        DropdownMenuItem(
//            text = { Text("Quadrant Note Method") },
//            onClick = {}
//        )
//    }
//    }
//}

