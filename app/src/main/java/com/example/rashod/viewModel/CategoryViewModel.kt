package com.example.rashod.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.rashod.model.Category

class CategoryViewModel:ViewModel() {
    private val _category = mutableStateListOf<Category>()
    val category: List<Category>
        get() = _category

    fun addCategory(category: String) {
        _category.add(Category(category))
    }

    fun getAllCategories(): List<Category> {
        return _category
    }
}