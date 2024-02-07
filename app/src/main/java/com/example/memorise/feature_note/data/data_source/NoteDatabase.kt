package com.example.memorise.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.memorise.feature_note.data.Converters
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.model.Folder

@Database(
    entities = [Note::class, Category::class, Folder::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val categoryDao: CategoryDao
    abstract val folderDao: FolderDao
    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}