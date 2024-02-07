package com.example.memorise.feature_note.presentation.add_edit_notes.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DisplayImage(
    imageBitmap: Bitmap?,
    modifier: Modifier = Modifier,
) {
    if (imageBitmap != null) {
        AsyncImage(
            model = imageBitmap,
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}
