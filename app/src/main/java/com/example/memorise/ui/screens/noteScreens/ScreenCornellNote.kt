package com.example.memorise.ui.screens.noteScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CornellNote(
    navController: NavController
) {
    val navController = rememberNavController()

    Topappbar (
        navController = navController,
        name = "Cornell Note"
    ) {
        cornellTextFields()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cornellTextFields(modifier: Modifier = Modifier) {
    var cornellTitleInput by remember { mutableStateOf("") }
    var cornellFirstKeyword by remember { mutableStateOf("") }
    var cornellFirstContent by remember { mutableStateOf("") }
    var cornellSecondKeyword by remember { mutableStateOf("") }
    var cornellSecondContent by remember { mutableStateOf("") }
    var cornellSummary by remember { mutableStateOf("") }

    val labelStyle = androidx.compose.ui.text.TextStyle(
        fontSize = 8.sp,
        fontWeight = FontWeight.Light,
        color = MaterialTheme.colorScheme.onSurface
    )

    Column(modifier = Modifier
        .padding(
            top = 76.dp,
        )
        .fillMaxSize()) {
        TextField(
            singleLine = true,
            label = {Text(text = "Title", style = labelStyle)},
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
            value = cornellTitleInput,
            onValueChange = {cornellTitleInput = it}
        )
        Row {
            TextField(
                label = {Text(text = "Keywords", style = labelStyle)},
                modifier = modifier
                    .fillMaxWidth(0.25f)
                    .fillMaxHeight(0.25f)
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
                value = cornellFirstKeyword,
                onValueChange = {cornellFirstKeyword = it}
            )
            TextField(
                label = {Text(text = "Content", style = labelStyle)},
                modifier = modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.25f)
                    .padding(
                        end = 8.dp,
                        bottom = 8.dp
                    ),
                value = cornellFirstContent,
                onValueChange = {cornellFirstContent = it}
            )
        }
        Row {
            TextField(
                label = {Text(text = "Keywords", style = labelStyle)},
                modifier = modifier
                    .fillMaxWidth(0.25f)
                    .fillMaxHeight(0.35f)
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
                value = cornellSecondKeyword,
                onValueChange = {cornellSecondKeyword = it}
            )
            TextField(
                label = {Text(text = "Content", style = labelStyle)},
                modifier = modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.35f)
                    .padding(
                        end = 8.dp,
                        bottom = 8.dp,
                    ),
                value = cornellSecondContent,
                onValueChange = {cornellSecondContent = it}
            )
        }
        TextField(
            label = {Text(text = "Summary", style = labelStyle)},
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp,
                ),
            value = cornellSummary,
            onValueChange = {cornellSummary = it}
        )
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CornellNote(
//    navController: NavController
//) {
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Scaffold(
//            modifier = Modifier,
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Text(text = "Cornell Note")
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
//            cornellTextFields()
//        }
//    }
//}