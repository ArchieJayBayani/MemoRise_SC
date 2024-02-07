package com.example.memorise.feature_note.presentation.Folders

import android.os.Build
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.memorise.R
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.presentation.ScreenNavigations.Screens
import com.example.memorise.feature_note.presentation.notes.components.getBackgroundColor


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun FolderItem(
    navController: NavController,
    folder: Folder,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit,
) {
    val folderImage = R.drawable.folder

    var showConfirmationDialog by remember { mutableStateOf(false) }

    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = {
                showConfirmationDialog = false
            },
            title = {
                Text("Delete Folder")
            },
            text = {
                Text("Are you sure you want to delete this folder?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteClick()
                        showConfirmationDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showConfirmationDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    var isExpanded by remember { mutableStateOf(false) }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            content = {
                DropdownMenuItem(
                    text = { Text("Rename")},
                    onClick = {
                        isExpanded = false
                        navController.navigate(Screens.FolderScreen.route + "?folderId=${folder.id}")
                    }
                )
            }
        )

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .clip(RoundedCornerShape(cornerRadius))
        .combinedClickable(
            onClick = {
                onItemClick.invoke()
            },
            onLongClick = {
                isExpanded = true
            }
        )
        .background(getBackgroundColor())
    ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
            ) {
                Image(
                    painter = painterResource(id = folderImage),
                    contentDescription = "Folder",
                    modifier = Modifier
                        .size(64.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 8.dp
                        )
                        .align(Alignment.CenterVertically),
                    text = folder.name,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {showConfirmationDialog = true},
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Category",
                    )
                }
            }
    }
}