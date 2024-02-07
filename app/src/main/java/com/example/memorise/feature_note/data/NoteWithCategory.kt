package com.example.memorise.feature_note.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.Note

data class NoteWithCategory(
    @Embedded val note: Note,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category?
)
