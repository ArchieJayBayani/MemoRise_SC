package com.example.memorise.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.memorise.feature_note.data.data_source.NoteDatabase
import com.example.memorise.feature_note.data.repository.NoteRepositoryImpl
import com.example.memorise.feature_note.domain.repository.NoteRepository
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.AddCategory
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.CategoryUseCases
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.DeleteCategoryUseCase
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.GetCategoriesListUseCase
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.GetCategoryUseCase
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.AddFolder
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.DeleteFolder
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.FolderUseCases
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.GetFolder
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.GetFolderList
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.AddNote
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.DeleteNoteUseCase
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.GetCategoryTitleForNoteUseCase
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.GetNoteUseCase
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.GetNotesByFolderIdUseCase
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.GetNotesUseCase
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.NoteUseCases
import com.example.memorise.feature_note.domain.use_case.NotesUseCase.SearchNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao, db.categoryDao, db.folderDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNote(repository),
            getNote = GetNoteUseCase(repository),
            searchNotes = SearchNotesUseCase(repository),
            getCategoryTitleForNote = GetCategoryTitleForNoteUseCase(repository),
            getNotesByFolderId = GetNotesByFolderIdUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCategoryUseCases(repository: NoteRepository): CategoryUseCases {
        return CategoryUseCases(
            getCategory = GetCategoryUseCase(repository),
            deleteCategory = DeleteCategoryUseCase(repository),
            addCategory = AddCategory(repository),
            getCategoryList = GetCategoriesListUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFolderUseCases(repository: NoteRepository): FolderUseCases {
        return FolderUseCases(
            getFolder = GetFolder(repository),
            deleteFolder = DeleteFolder(repository),
            addFolder = AddFolder(repository),
            getFolderList = GetFolderList(repository)
        )
    }

    @Provides
    fun provideContext(application: Application): Context = application
}