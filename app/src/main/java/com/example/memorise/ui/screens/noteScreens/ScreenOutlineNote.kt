package com.example.memorise.ui.screens.noteScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun outlineNote(
    navController: NavController
) {
    val navController = rememberNavController()

    Topappbar (
        navController = navController,
        name = "Outline Note"
    ) {
        outlineNote()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun outlineNote() {

    var outlineFirstTitle by remember { mutableStateOf("") }
    var outlineFirstContent by remember { mutableStateOf("") }
    var outlineSecondTitle by remember { mutableStateOf("") }
    var outlineSecondContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(
                top = 72.dp
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column() {
            TextField(
                label = {Text(text = "Title")},
                value = outlineFirstTitle,
                onValueChange = {outlineFirstTitle = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    )
            )
        }
        Column() {
            TextField(
                label = {Text(text = "Content")},
                value = outlineFirstContent,
                onValueChange = {outlineFirstContent = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .padding(
                        start = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    )
            )
        }
        Column() {
            TextField(
                label = {Text(text = "Title")},
                value = outlineSecondTitle,
                onValueChange = {outlineSecondTitle = it}  ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    )
            )
        }
        Column() {
            TextField(
                label = {Text(text = "Content")},
                value = outlineSecondContent,
                onValueChange = {outlineSecondContent = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .padding(
                        start = 8.dp,
                        bottom = 8.dp,
                        end = 8.dp
                    )
            )
        }
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun outlineNote(
//    navController: NavController
//) {
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Scaffold(
//            modifier = Modifier,
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Text(text = "Outline Note")
//                    },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            navController.navigate(Screens.MainScreen.route)
//                        }) {
//                            Icon(
//                                imageVector = Icons.Default.ArrowBack,
//                                contentDescription = "Back"
//                            )
//                        }
//                    },
//                )
//            }
//        ){
//            outlineNote()
//        }
//    }
//}