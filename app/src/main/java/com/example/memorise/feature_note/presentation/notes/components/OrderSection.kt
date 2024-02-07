package com.example.memorise.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.memorise.feature_note.domain.util.NoteOrder
import com.example.memorise.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    var showMoreVert by remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = { showMoreVert = !showMoreVert }
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Sorting Options"
        )
    }
    DropdownMenu(
        expanded = showMoreVert,
        onDismissRequest = { showMoreVert = false }) {
        DefaultRadioButton(
            text = "Title",
            selected = noteOrder is NoteOrder.Title,
            onSelect = { onOrderChange(NoteOrder.Title(noteOrder.orderType)) }
        )
        DefaultRadioButton(
            text = "Date",
            selected = noteOrder is NoteOrder.Date,
            onSelect = { onOrderChange(NoteOrder.Date(noteOrder.orderType)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DefaultRadioButton(
            text = "Ascending",
            selected = noteOrder.orderType is OrderType.Ascending,
            onSelect = {
                onOrderChange(noteOrder.copy(OrderType.Ascending))
            }
        )
        DefaultRadioButton(
            text = "Descending",
            selected = noteOrder.orderType is OrderType.Descending,
            onSelect = {
                onOrderChange(noteOrder.copy(OrderType.Descending))
            }
        )
    }
}