package com.example.memorise.feature_note.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val keyword1: String? = null,
    val keyword2: String? = null,
    val keyword3: String? = null,
    val keyword4: String? = null,
    val content1: String? = null,
    val content2: String? = null,
    val content3: String? = null,
    val content4: String? = null,
    val content5: String? = null,
    val summary: String? = null,
    val category_id: Int? = null,
    val imageBytes: ByteArray? = null,
    val folderId: Int? = null,
    val noteType: NoteType,
    val timestamp: Long = System.currentTimeMillis(),
    val segments: List<FormattedSegment>
)

data class FormattedSegment(
    val text: String,
    val isBold: Boolean = false,
    val isItalic: Boolean = false,
    val isUnderlined: Boolean = false,
    val start: Int,
    val end: Int,
    val fieldId: Int,
)

enum class NoteType {
    BASIC, CORNELL, OUTLINE, QUADRANT, LADDER, IMAGE
}



class InvalidNoteException(message: String): Exception(message)
