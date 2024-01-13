package com.example.memorise.ui.screens.noteScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun basicNote(
    navController: NavController
) {
    val navController = rememberNavController()

    Topappbar (
        navController = navController,
        name = "Basic Note"
    ) {
        basicTextFields()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun basicTextFields(modifier: Modifier = Modifier) {
    var basicTitleInput by remember { mutableStateOf("") }
    var basicNoteInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(
                top = 76.dp,
            )
            .fillMaxWidth()
    ) {
        TextField(
            label= {Text(text = "Title")},
            value = basicTitleInput,
            onValueChange = { basicTitleInput = it },
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                )
                .clip(RoundedCornerShape(12.dp))
        )
        TextField(
            label= {Text(text = "Content")},
            value = basicNoteInput,
            onValueChange = { basicNoteInput = it },
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
                .clip(RoundedCornerShape(12.dp))
        )
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun basicNote(
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
//                        Text(text = "Basic Notes")
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
//            basicTextFields()
//        }
//    }
//}