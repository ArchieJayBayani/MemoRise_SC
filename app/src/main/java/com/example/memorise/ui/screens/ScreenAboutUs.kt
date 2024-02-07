package com.example.memorise.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.memorise.feature_note.presentation.ScreenNavigations.AppDrawer
import com.example.memorise.feature_note.presentation.ScreenNavigations.NavigationItem
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aboutUsScreen(
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
            title = "About Us",
        ) {
            aboutUs()
        }
    }
}

@Composable
fun aboutUs(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 72.dp,
                bottom = 72.dp
            )
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,

    ) {
        Text("MemoRise is a mobile application that implements different note-taking methods to improve students' note-taking",
            fontSize= 20.sp,
            textAlign = TextAlign
                .Start
        )
        Spacer(modifier = Modifier
            .padding(top = 120.dp))

        Text("Developers:",
            fontSize= 20.sp,
            textAlign = TextAlign
                .Start,
        )
        Text("Karlos Louis Angelo A. Garcia",
            fontSize= 20.sp,
            textAlign = TextAlign
                .Start
        )
        Text("Archie Jay L. Bayani",
            fontSize= 20.sp,
            textAlign = TextAlign
                .Start
        )
    }


}