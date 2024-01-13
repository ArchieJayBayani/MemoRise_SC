package com.example.memorise.ui.screens.noteScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
@Composable
fun LadderNote(
    navController: NavController
) {
    val navController = rememberNavController()

    Topappbar (
        navController = navController,
        name = "Ladder Note"
    ) {
        ladderNote()
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ladderNote(modifier: Modifier = Modifier) {

    var title by remember { mutableStateOf("") }
    var firstTextField by remember { mutableStateOf("") }
    var secondTextField by remember { mutableStateOf("") }
    var thirdTextField by remember { mutableStateOf("") }
    var fourthTextField by remember { mutableStateOf("") }
    var fifthTextField by remember { mutableStateOf("") }


    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
            .padding(
                start = 8.dp,
                top = 72.dp,
                bottom = 8.dp,
                end = 8.dp

            )
    ) {
        TextField(
            value = title,
            onValueChange = {title = it},
            modifier = modifier
                .padding(
                    bottom = 16.dp
                )
                .fillMaxWidth(),
            label = {Text(text = "Title")}
        )
        TextField(
            value = firstTextField,
            onValueChange = {firstTextField = it},
            modifier = modifier
                .padding(
                    bottom = 8.dp
                )
//                .height(80.dp)
                .width(100.dp),
            label = {Text(text = "Content")}
        )
        TextField(
            value = secondTextField,
            onValueChange = {secondTextField = it},
            modifier = modifier
                .padding(
                    bottom = 8.dp
                )
                .height(80.dp)
                .width(150.dp),
            label = {Text(text = "Content")}
        )
        TextField(
            value = thirdTextField,
            onValueChange = {thirdTextField = it},
            modifier = modifier
                .padding(
                    bottom = 8.dp
                )
                .height(80.dp)
                .width(200.dp),
            label = {Text(text = "Content")}
        )
        TextField(
            value = fourthTextField,
            onValueChange = {fourthTextField},
            modifier = modifier
                .padding(
                    bottom = 8.dp
                )
                .height(80.dp)
                .width(250.dp),
            label = {Text(text = "Content")}
        )
        TextField(
            value = fifthTextField,
            onValueChange = {fifthTextField = it},
            modifier = modifier
                .padding(
                    bottom = 8.dp
                )
                .height(80.dp)
                .width(300.dp),
            label = {Text(text = "Content")}
        )
    }

}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun LadderNote(
//    navController: NavController,
//    name: String = "Ladder Notesie"
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
//                        Text(name)
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
//            ladderNote()
//        }
//    }
//}