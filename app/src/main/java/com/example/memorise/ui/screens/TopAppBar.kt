package com.example.memorise.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.presentation.add_edit_notes.components.CategoryDropdown

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topappbar(
    navController: NavController,
    name: String,
    showCategoryDropdown: Boolean = false,
    categories: List<Category> = emptyList(),
    selectedCategory: Category? = null,
    onCategorySelected: (Category) -> Unit = {},
    content: @Composable () -> Unit,
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
                        Text(name)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        if (showCategoryDropdown){
                            CategoryDropdown(
                                categories = categories,
                                selectedCategory = selectedCategory,
                                onCategorySelected = onCategorySelected
                            )
                        }
                    }
                )
            }
        ){
            content()
        }
    }
}