package com.example.memorise.feature_note.presentation.Folders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.FolderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val folderUseCases: FolderUseCases
) : ViewModel() {
    private val _folderState = MutableStateFlow(FolderState())
    val folderState: Flow<FolderState> = _folderState.asStateFlow()

    private var recentlyDeletedFolder: Folder? = null
    private var getFolderJob : Job? = null



    fun onFolderEvent(event: FoldersEvent) {
        when(event) {
            is FoldersEvent.ListFolder -> {
                viewModelScope.launch {
                    getFolders()
                }
            }
            is FoldersEvent.DeleteFolder -> {
                viewModelScope.launch {
                    folderUseCases.deleteFolder(event.folder)
                    recentlyDeletedFolder = event.folder
                }
            }
            is FoldersEvent.RestoreFolder -> {
                viewModelScope.launch {
                    folderUseCases.addFolder(recentlyDeletedFolder ?: return@launch)
                    recentlyDeletedFolder = null
                }
            }
        }
    }
    private fun getFolders() {
        getFolderJob?.cancel()
        getFolderJob = folderUseCases.getFolderList()
            .onEach { folder ->
                Log.d("FolderViewModel", "Folders retrieved: $folder")
                _folderState.value = _folderState.value.copy(
                    folder = folder
                )
            }
            .launchIn(viewModelScope)
    }
}