package com.example.memorise.feature_note.data.repository

import com.example.memorise.feature_note.data.NoteWithCategory
import com.example.memorise.feature_note.data.NoteWithFolder
import com.example.memorise.feature_note.data.data_source.CategoryDao
import com.example.memorise.feature_note.data.data_source.FolderDao
import com.example.memorise.feature_note.data.data_source.NoteDao
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.model.Note
import com.example.memorise.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
    private val categoryDao: CategoryDao,
    private val folderDao: FolderDao,
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }
    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }
    override suspend fun insertNote(note: Note) {
        return noteDao.insertUnifiedNote(note)
    }
    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteUnifiedNote(note)
    }
    override fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query)
    }
    override fun getNotesWithCategories(): Flow<List<NoteWithCategory>> {
        return noteDao.getNotesWithCategories()
    }
    override fun getNotesByFolderId(folderId: Int?): Flow<List<Note>> {
        return noteDao.getNotesByFolderId(folderId)
    }
    override fun getNotesWithFolders(): Flow<List<NoteWithFolder>> {
        return noteDao.getNotesWithFolders()
    }
    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }
    override suspend fun getCategoryById(id: Int): Category? {
        return categoryDao.getCategoryById(id)
    }
    override suspend fun insertCategory(category: Category) {
        return categoryDao.insertCategory(category)
    }
    override suspend fun deleteCategory(category: Category) {
        return categoryDao.deleteCategory(category)
    }
    override suspend fun getTitleForId(id: Int): String? {
        return categoryDao.getTitleForId(id)
    }
    override fun getAllFolders(): Flow<List<Folder>> {
        return folderDao.getAllFolders()
    }
    override suspend fun getFolderById(id: Int): Folder? {
        return folderDao.getFolderById(id)
    }
    override suspend fun insertFolder(folder: Folder) {
        return folderDao.insertFolder(folder)
    }
    override suspend fun deleteFolder(folder: Folder) {
        return folderDao.deleteFolder(folder)
    }
}