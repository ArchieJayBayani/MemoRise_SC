package com.example.memorise.feature_note.domain.repository

import com.example.memorise.feature_note.data.NoteWithCategory
import com.example.memorise.feature_note.data.NoteWithFolder
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
    suspend fun deleteNote(note: Note)
    fun searchNotes(query: String): Flow<List<Note>>
    fun getNotesWithCategories(): Flow<List<NoteWithCategory>>
    fun getNotesByFolderId(folderId: Int?): Flow<List<Note>>
    fun getNotesWithFolders(): Flow<List<NoteWithFolder>>

    fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoryById(id: Int): Category?
    suspend fun insertCategory(category: Category)
    suspend fun deleteCategory(category: Category)
    suspend fun getTitleForId(id: Int): String?

    fun getAllFolders(): Flow<List<Folder>>
    suspend fun getFolderById(id: Int): Folder?
    suspend fun insertFolder(folder: Folder)
    suspend fun deleteFolder(folder: Folder)
}
