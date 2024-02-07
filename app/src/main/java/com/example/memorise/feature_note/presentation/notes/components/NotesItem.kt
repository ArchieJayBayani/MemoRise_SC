package com.example.memorise.feature_note.presentation.notes.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.model.NoteType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun NoteItem(
    note: Note,
    category: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    scaffoldState: ScaffoldState,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit,
) {


    var showConfirmationDialog by remember { mutableStateOf(false) }

    if (showConfirmationDialog) {
        AlertDialog(
            onDismissRequest = {
                showConfirmationDialog = false
            },
            title = {
                Text("Delete Note")
            },
            text = {
                Text("Are you sure you want to delete this note?")
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



    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .clip(RoundedCornerShape(cornerRadius))
        .clickable { onItemClick.invoke() }
        .background(getBackgroundColor())
//        .background(color = Color(0xFF49454F.toInt()))
    ) {

        Column(
            modifier = Modifier
                .padding(top = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                when (note.noteType) {
                    NoteType.IMAGE -> {
                        AsyncImage(
                            model = note.imageBytes,
                            contentDescription = "Preview Image",
                            modifier = Modifier
                                .size(96.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(color = Color(0xFF696372.toInt()))
                        )
                    }
                    else -> {}
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row() {
                        Text(
                            modifier = Modifier
                                .padding(
                                    top = 8.dp,
                                    start = 8.dp,
                                    bottom = 8.dp,
                                    end = 8.dp
                                )
                                .weight(1f),
                            text = note.title,
                            style = MaterialTheme.typography.titleLarge,
                            overflow = TextOverflow.Ellipsis
                        )
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(getBackgroundColorForCategory())
//                            .background(color = Color(0xFF696372.toInt()))
                            .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        top = 8.dp,
                                        start = 8.dp,
                                        bottom = 8.dp,
                                        end = 8.dp
                                    ),
                                text = category,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }


                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp
                            ),
                        text = note.content1 ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            start = 12.dp,
                            bottom = 4.dp,
                            ),
                    text = formatTimestamp(note.timestamp),
                    style = MaterialTheme.typography.bodySmall,
                )
                var noteTypetext = when (note.noteType) {
                    NoteType.BASIC -> "Basic Note"
                    NoteType.QUADRANT -> "Quadrant Note"
                    NoteType.LADDER -> "Ladder Note"
                    NoteType.OUTLINE -> "Outline Note"
                    NoteType.CORNELL -> "Cornell Note"
                    NoteType.IMAGE -> "Image Note"
                }
                Text(
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp
                        ),
                    text = noteTypetext,
                    style = MaterialTheme.typography.bodySmall,
                )
                IconButton(
                    onClick = { showConfirmationDialog = true },
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note"
                    )
                }
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format.format(date)
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun getBackgroundColor(): Color {
    val context = LocalContext.current
    val isDarkMode = context.resources.configuration.isNightModeActive
    return if (isDarkMode) {
        Color(0xFF49454F.toInt())
    } else {
        Color(0xFFE6E0EC.toInt())
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun getBackgroundColorForCategory(): Color {
    val context = LocalContext.current
    val isDarkMode = context.resources.configuration.isNightModeActive
    return if (isDarkMode) {
        Color(0xFF696372.toInt())
    } else {
        Color(0xFFC5B4D5.toInt())
    }
}
