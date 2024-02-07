package com.example.memorise.feature_note.presentation.Folders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.memorise.feature_note.presentation.notes.components.getBackgroundColor

@Composable
fun ListBackStackItem(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    onItemClick: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .clip(RoundedCornerShape(cornerRadius))
        .clickable { onItemClick.invoke() }
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
            Text(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        end = 8.dp
                    )
                    .align(Alignment.CenterVertically),
                text = ". . .",
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}