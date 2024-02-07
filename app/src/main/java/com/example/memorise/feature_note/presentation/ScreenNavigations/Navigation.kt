package com.example.memorise.feature_note.presentation.ScreenNavigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.memorise.feature_note.presentation.add_edit_categories.AddEditCategoryScreen
import com.example.memorise.feature_note.presentation.add_edit_folder.AddEditFolderScreen
import com.example.memorise.feature_note.presentation.notes.MainScreen
import com.example.memorise.feature_note.presentation.category.CategoryScreen
import com.example.memorise.ui.screens.SelectionScreen
import com.example.memorise.ui.screens.aboutUsScreen
import com.example.memorise.ui.screens.noteScreens.CornellNote
import com.example.memorise.ui.screens.noteScreens.ImageNoteScreen
import com.example.memorise.ui.screens.noteScreens.LadderNote
import com.example.memorise.ui.screens.noteScreens.OutlineNote
import com.example.memorise.ui.screens.noteScreens.QuadrantNote
import com.example.memorise.ui.screens.noteScreens.basicNote
import com.example.memorise.ui.screens.settingScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {

        composable(
            route = Screens.MainScreen.route,
        ) {
            MainScreen(
                navController = navController,
                items = getNavigationItems(navController),
            )
        }

        composable(
            route = Screens.SettingScreen.route,
        ) {
            settingScreen(
                navController = navController,
                items = getNavigationItems(navController)
            )
        }

        composable(
            route = Screens.AboutUsScreen.route,
        ) {
            aboutUsScreen(
                navController = navController,
                items = getNavigationItems(navController)
            )
        }

        composable(
            route = Screens.NoteSelectionScreen.route,
        ) {
            SelectionScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }

        composable(
            route = Screens.BasicNoteScreen.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            basicNote(
                navController = navController)
        }

        composable(
            route = Screens.CornellNoteScreen.route + "?noteId={noteId}",
            arguments = listOf (
                navArgument (
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            CornellNote(
            navController = navController)
        }


        composable(
            route = Screens.OutlineNoteScreen.route + "?noteId={noteId}",
            arguments = listOf (
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            OutlineNote(
                navController = navController
            )
        }

        composable(
            route = Screens.QuadrantNoteScreen.route + "?noteId={noteId}",
            arguments = listOf (
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            QuadrantNote(
                navController = navController
            )
        }

        composable(
            route = Screens.LadderNoteScreen.route + "?noteId={noteId}",
            arguments = listOf (
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            LadderNote(
                navController = navController
            )
        }

        composable(
            route = Screens.CategoryScreen.route
        ) {
            CategoryScreen(
                navController = navController,
                items = getNavigationItems(navController)
            )
        }
        composable(
            route = Screens.AddEditCategoryScreen.route + "?categoryId={categoryId}",
            arguments = listOf(
                navArgument(
                    name = "categoryId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            AddEditCategoryScreen(navController = navController)
        }
        composable(
            route = Screens.ImageNoteScreen.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            ImageNoteScreen(navController = navController)
        }
        composable(
            route = Screens.FolderScreen.route + "?folderId={folderId}",
            arguments = listOf(
                navArgument(
                    name = "folderId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )
        ) {
            AddEditFolderScreen(navController = navController)
        }
    }
}

