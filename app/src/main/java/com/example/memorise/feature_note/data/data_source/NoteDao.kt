package com.example.memorise.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.memorise.feature_note.data.NoteWithCategory
import com.example.memorise.feature_note.data.NoteWithFolder
import com.example.memorise.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnifiedNote(note: Note)

    @Delete
    suspend fun deleteUnifiedNote(note: Note)

    @Query ("SELECT * FROM Note WHERE title LIKE '%' || :query || '%' ")
    fun searchNotes (query: String): Flow<List<Note>>

    @Transaction
    @Query("SELECT * FROM Note")
    fun getNotesWithCategories(): Flow<List<NoteWithCategory>>

    @Query("SELECT * FROM Note WHERE folderId = :folderId OR folderId IS NULL")
    fun getNotesByFolderId(folderId: Int?): Flow<List<Note>>

    @Transaction
    @Query("SELECT * FROM Note")
    fun getNotesWithFolders(): Flow<List<NoteWithFolder>>
}