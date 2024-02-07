package com.example.memorise.feature_note.presentation.add_edit_notes.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.presentation.notes.components.getBackgroundColor

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun FolderDropdown(
    modifier: Modifier = Modifier,
    folders: List<Folder>,
    selectedFolder: Folder?,
    onFolderSelected: (Folder) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(
                start = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .clickable { expanded = !expanded }
            .clip(RoundedCornerShape(12.dp))
            .background(getBackgroundColor())
            .wrapContentHeight()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown",
            modifier = Modifier
                .padding(end = 16.dp)
        )
        Text(
            text = selectedFolder?.name ?: "Folder",
            modifier = Modifier.padding(end = 4.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            folders.forEach { folder ->
                DropdownMenuItem(
                    text = { Text(folder.name) },
                    onClick = {
                        onFolderSelected(folder)
                        expanded = false
                    }
                )
            }
        }
    }
}