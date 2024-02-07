package com.example.memorise.feature_note.presentation.add_edit_folder.Components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorise.feature_note.domain.model.Folder
import com.example.memorise.feature_note.domain.model.InvalidNoteException
import com.example.memorise.feature_note.domain.use_case.FolderUseCase.FolderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditFolderViewModel @Inject constructor(
    private val folderUseCases: FolderUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _folderTitle = mutableStateOf(FolderTextFieldState())
    val folderTitle: State<FolderTextFieldState> = _folderTitle

    private val _eventFlow = MutableSharedFlow<UiFolderEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentFolderId: Int? = null
    init {
        savedStateHandle.get<Int>("folderId")?.let { folderId ->
            if (folderId != -1) {
                viewModelScope.launch {
                    folderUseCases.getFolder(folderId)?.also {folder ->
                        currentFolderId = folder.id
                        _folderTitle.value = folderTitle.value.copy(
                            text = folder.name
                        )
                    }
                }
            }
        }
    }
    fun onEvent(event: AddEditFolderEvent) {
        when(event) {
            is AddEditFolderEvent.EnteredTitle -> {
                _folderTitle.value = folderTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditFolderEvent.SaveFolder -> {
                viewModelScope.launch {
                    try {
                        val parentId = currentFolderId
                        folderUseCases.addFolder(
                            Folder(
                                id = currentFolderId,
                                name = folderTitle.value.text,
                            )
                        )
                        _eventFlow.emit(UiFolderEvent.SaveFolder)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiFolderEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save folder"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiFolderEvent {
        data class ShowSnackbar(val message: String): UiFolderEvent()
        object SaveFolder: UiFolderEvent()
    }
}