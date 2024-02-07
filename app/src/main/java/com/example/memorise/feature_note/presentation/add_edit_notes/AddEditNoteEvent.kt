package com.example.memorise.feature_note.presentation.add_edit_notes

import android.net.Uri
import androidx.compose.ui.focus.FocusState
import com.example.memorise.feature_note.domain.model.NoteType

sealed class AddEditNoteEvent{
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class EnteredContent1(val value: String) : AddEditNoteEvent()
    data class EnteredContent2(val value: String) : AddEditNoteEvent()
    data class EnteredContent3(val value: String) : AddEditNoteEvent()
    data class EnteredContent4(val value: String) : AddEditNoteEvent()
    data class EnteredContent5(val value: String) : AddEditNoteEvent()
    data class EnteredKeyword1(val value: String) : AddEditNoteEvent()
    data class EnteredKeyword2(val value: String) : AddEditNoteEvent()
    data class EnteredKeyword3(val value: String) : AddEditNoteEvent()
    data class EnteredKeyword4(val value: String) : AddEditNoteEvent()
    data class EnteredSummary(val value: String) : AddEditNoteEvent()
    data class EnteredNoteType(val noteType: NoteType): AddEditNoteEvent()
    data class ImageSelected(val uri: Uri) : AddEditNoteEvent()
    object ToggleBold : AddEditNoteEvent()
    object ToggleItalic : AddEditNoteEvent()
    object ToggleUnderline : AddEditNoteEvent()
    object SaveNote : AddEditNoteEvent()
}
