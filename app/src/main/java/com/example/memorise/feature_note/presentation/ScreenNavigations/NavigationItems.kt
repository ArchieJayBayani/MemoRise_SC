package com.example.memorise.feature_note.presentation.ScreenNavigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: (NavController) -> Unit
)

//function that calls on the list of navigation items and calls them to the main activity
fun getNavigationItems(
    navController: NavController
): List<NavigationItem> {
    return listOf(
        NavigationItem(
            title = "notes",
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            route = {navController.navigate(Screens.MainScreen.route)}
        ),
        NavigationItem(
            title = "Category",
            selectedIcon = Icons.Filled.AddCircle,
            unselectedIcon = Icons.Outlined.AddCircle,
            route = {navController.popBackStack(route = Screens.CategoryScreen.route, inclusive = false)
            navController.navigate(Screens.CategoryScreen.route)}
        ),
//        NavigationItem(
//            title = "Settings",
//            selectedIcon = Icons.Filled.Settings,
//            unselectedIcon = Icons.Outlined.Settings,
//            route = {navController.popBackStack(route = Screens.SettingScreen.route, inclusive = false)
//            navController.navigate(Screens.SettingScreen.route)}
//
//        ),
        NavigationItem(
            title = "About Us",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = {navController.navigate(Screens.AboutUsScreen.route)}
        ),
    )
}