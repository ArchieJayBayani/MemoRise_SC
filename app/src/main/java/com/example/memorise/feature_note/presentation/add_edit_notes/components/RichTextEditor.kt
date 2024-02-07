package com.example.memorise.feature_note.presentation.add_edit_notes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.font.FontWeight
import com.example.memorise.feature_note.domain.model.FormattedSegment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.TextStyle


//@Composable
//fun RichTextEditor(
//    textState: MutableState<String>,
//    selectedTextFieldId: Int,
//    formattedSegments: List<FormattedSegment>,
//    onToggleBold: () -> Unit,
//    onToggleItalic: () -> Unit,
//    onToggleUnderline: () -> Unit,
//    onSelectionChange: (Int, Int) -> Unit
//) {
//    Column() {
//        BasicTextField(
//            value = textState.value,
//            onValueChange = { newText ->
//                textState.value = newText
//            },
//            onSelectionChange = { newSelection ->
//                onSelectionChange(selectedTextFieldId, newSelection.start, newSelection.end)
//            },
//            textStyle = TextStyle(
//                fontWeight = if (isBoldSelected(selectedTextFieldId, formattedSegments)) FontWeight.Bold else FontWeight.Normal,
//                fontStyle = if (isItalicSelected(selectedTextFieldId, formattedSegments)) FontStyle.Italic else FontStyle.Normal,
//                textDecoration = if (isUnderlineSelected(selectedTextFieldId, formattedSegments)) TextDecoration.Underline else TextDecoration.None
//            )
//        )
//    }
//}
//
//fun isBoldSelected(selectedTextFieldId: Int, formattedSegments: List<FormattedSegment>): Boolean {
//    val selectedSegments = formattedSegments.filter { it.fieldId == selectedTextFieldId }
//    return selectedSegments.any { it.isBold }
//}
//
//fun isItalicSelected(selectedTextFieldId: Int, formattedSegments: List<FormattedSegment>): Boolean {
//    val selectedSegments = formattedSegments.filter { it.fieldId == selectedTextFieldId }
//    return selectedSegments.any { it.isItalic }
//}
//
//fun isUnderlineSelected(selectedTextFieldId: Int, formattedSegments: List<FormattedSegment>): Boolean {
//    val selectedSegments = formattedSegments.filter { it.fieldId == selectedTextFieldId }
//    return selectedSegments.any { it.isUnderlined }
//}
