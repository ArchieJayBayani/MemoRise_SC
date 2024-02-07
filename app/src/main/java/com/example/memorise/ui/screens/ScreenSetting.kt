package com.example.memorise.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.memorise.feature_note.presentation.ScreenNavigations.AppDrawer
import com.example.memorise.feature_note.presentation.ScreenNavigations.NavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settingScreen(
    navController: NavController,
    items: List<NavigationItem>
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AppDrawer(
            items = items,
            onItemClick = { item ->
                          item.route(navController)
            },
            navController = navController,
            selectedItemIndex = 2,
            title = "Settings"
        ) {

        }
    }
}