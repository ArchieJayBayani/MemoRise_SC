package com.example.memorise.feature_note.presentation.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memorise.feature_note.domain.model.Category
import com.example.memorise.feature_note.domain.use_case.CategoryUseCase.CategoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {
    private val _categoryState = mutableStateOf(CategoryState())
    val categoryState: State<CategoryState> = _categoryState

    private var recentlyDeletedCategory: Category? = null
    private var getCategoryJob : Job? = null

    fun onCategoryEvent(event: CategoriesEvent) {
        when(event) {
            is CategoriesEvent.ListCategory -> {
                viewModelScope.launch {
                    getCategories()
                }
            }
            is CategoriesEvent.DeleteCategory -> {
                viewModelScope.launch {
                    categoryUseCases.deleteCategory(event.category)
                    recentlyDeletedCategory = event.category
                }
            }
            is CategoriesEvent.RestoreCategory -> {
                viewModelScope.launch {
                    categoryUseCases.addCategory(recentlyDeletedCategory ?: return@launch)
                    recentlyDeletedCategory = null
                }
            }
        }

    }
    private fun getCategories() {
        getCategoryJob?.cancel()
        getCategoryJob = categoryUseCases.getCategoryList()
            .onEach { categories ->
                _categoryState.value = categoryState.value.copy(
                    categories = categories
                )
            }
            .launchIn(viewModelScope)
    }
}