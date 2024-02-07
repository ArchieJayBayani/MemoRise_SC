package com.example.memorise.feature_note.presentation.add_edit_categories.components

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.model.InvalidNoteException
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCategoryViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _categoryTitle = mutableStateOf(CategoryTextFieldState())
    val categoryTitle: State<CategoryTextFieldState> = _categoryTitle

    private val _eventFlow = MutableSharedFlow<UiCategoryEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentCategoryId: Int? = null
    init {
        savedStateHandle.get<Int>("categoryId")?.let { categoryId ->
            if(categoryId != -1) {
                viewModelScope.launch {
                    categoryUseCase.getCategory(categoryId)?.also {category ->
                        Log.d("AddEditCategoryViewModel", "Retrieved category: $category")

                        currentCategoryId = category.id
                        _categoryTitle.value = categoryTitle.value.copy(
                            text = category.categoryTitle
                        )
                    }
                }
            }
        }
    }
    fun onEvent(event: AddEditCategoryEvent) {
        when(event) {
            is AddEditCategoryEvent.EnteredTitle -> {
                _categoryTitle.value = categoryTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditCategoryEvent.SaveCategory -> {
                viewModelScope.launch {
                    try {
                        categoryUseCase.addCategory(
                            Category(
                                categoryTitle = categoryTitle.value.text,
                                id = currentCategoryId
                            )
                        )
                        _eventFlow.emit(UiCategoryEvent.SaveCategory)
                    } catch (e: InvalidNoteException) {
                        _eventFlow.emit(
                            UiCategoryEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save category"
                            )
                        )
                    }

                }
            }
        }
    }
    sealed class UiCategoryEvent {
        data class ShowSnackbar(val message: String): UiCategoryEvent()
        object SaveCategory: UiCategoryEvent()
    }
}