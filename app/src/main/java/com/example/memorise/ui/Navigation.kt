package com.example.memorise.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memorise.ui.screens.MainScreen
import com.example.memorise.ui.screens.noteScreens.CornellNote
import com.example.memorise.ui.screens.noteScreens.QuadrantNote
import com.example.memorise.ui.screens.SelectionScreen
import com.example.memorise.ui.screens.aboutUsScreen
import com.example.memorise.ui.screens.noteScreens.basicNote
import com.example.memorise.ui.screens.noteScreens.chartingNote
import com.example.memorise.ui.screens.getNavigationItems
import com.example.memorise.ui.screens.noteScreens.LadderNote
import com.example.memorise.ui.screens.noteSelectScreen
import com.example.memorise.ui.screens.noteScreens.outlineNote
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
                navController = navController
            )
        }
        composable(
            route = Screens.AboutUsScreen.route,
        ) {
            aboutUsScreen(
                navController = navController
            )
        }
        composable(
            route = Screens.NoteSelectScreen.route
        ) {
            noteSelectScreen(navController = navController)
        }
        composable(
            route = Screens.BasicNoteScreen.route,
        ) {
            basicNote(
                navController = navController
            )
        }
        composable(
            route = Screens.ChartingNoteScreen.route,
        ) {
            chartingNote(
                navController = navController
            )
        }
        composable(
            route = Screens.CornellNoteScreen.route,
        ) {
            CornellNote(
                navController = navController
            )
        }
        composable(
            route = Screens.OutlineNoteScreen.route,
        ) {
            outlineNote(
                navController = navController
            )
        }
        composable(
            route = Screens.QuadrantNoteScreen.route,
        ) {
            QuadrantNote(
                navController = navController
            )
        }
        composable(
            route = Screens.NoteSelectionScreen.route,
        ) {
            SelectionScreen(
                navController = navController
            )
        }
        composable(
            route = Screens.LadderNoteScreen.route,
        ) {
            LadderNote(
                navController = navController
            )
        }
    }
}

