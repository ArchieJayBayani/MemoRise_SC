package com.example.memorise.feature_note.domain.use_case.CategoryUseCase

data class CategoryUseCases(
    val getCategoryList: GetCategoriesListUseCase,
    val getCategory: GetCategoryUseCase,
    val deleteCategory: DeleteCategoryUseCase,
    val addCategory: AddCategory,
)
