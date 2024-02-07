package com.example.memorise.feature_note.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.model.Note

data class NoteWithFolder(
    @Embedded val note: Note,
    @Relation(
        parentColumn = "folderId",
        entityColumn = "id"
    )
    val folder: Folder
)
