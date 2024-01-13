package com.example.memorise

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.memorise.ui.Navigation
import com.example.memorise.ui.theme.MemoRiseTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoRiseTheme {
                //use Navigation(), starts the app from the starting destination(screen) in Navigation.kt
                Navigation()


//                  QuadrantNote()
//                mainScreen(items = getNavigationItems())
//                CornellNote()
//                basicNote()
//                MainScreen(items = getNavigationItems())
//                settingScreen()
            }
        }
    }
}
