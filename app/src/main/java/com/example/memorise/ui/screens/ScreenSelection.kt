package com.example.memorise.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorise.ui.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    navController: NavController
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Selection Screen")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(Screens.MainScreen.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            }
        ){
            selectionScreen(navController = navController)
        }
    }
}
@Composable
fun selectionScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(
                top = 72.dp
            )
            .fillMaxWidth()
    ) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.BasicNoteScreen.route)}) {
            Text(text = "Basic Note",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.CornellNoteScreen.route)}) {
            Text(text = "Cornell Note Method",
                textAlign = TextAlign.Left)

        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.OutlineNoteScreen.route)}) {
            Text(text = "Outline Note Method",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.ChartingNoteScreen.route)}) {
            Text(
                text = "Charting Note Method",
                textAlign = TextAlign.Left,
               )
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.QuadrantNoteScreen.route)}) {
            Text(text = "Quadrant Note Method",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {navController.navigate(Screens.LadderNoteScreen.route)}) {
            Text(text = "Ladder",
                textAlign = TextAlign.Left)
        }
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(text = "Graph",
                textAlign = TextAlign.Left)
        }
    }


}