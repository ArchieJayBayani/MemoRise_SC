package com.example.memorise.feature_note.presentation.notes.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.memorise.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar() {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            androidx.compose.material3.BottomAppBar(
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource (id = R.drawable.format_bold_fill0_wght400_grad0_opsz24),
                            contentDescription = "Bold")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource (id = R.drawable.format_italic_fill0_wght400_grad0_opsz24),
                            contentDescription = "Italic")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource (id = R.drawable.format_underlined_fill0_wght400_grad0_opsz24),
                            contentDescription = "Underline")
                    }
                }
            )
        }
        ) {
    }
}