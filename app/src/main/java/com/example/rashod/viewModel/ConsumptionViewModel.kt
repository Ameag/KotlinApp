package com.example.rashod.viewModel

import androidx.compose.runtime.mutableStateListOf
import com.example.rashod.model.Category
import com.example.rashod.model.Сonsumption

class ConsumptionViewModel {
    private val _сonsumption = mutableStateListOf<Сonsumption>()
    val category: List<Сonsumption>
        get() = _сonsumption

    var selectedCategory: Category? = null

    fun addСonsumption(sum: Int,сonsumption: Category) {
        _сonsumption.add(Сonsumption(sum,сonsumption))
    }

    fun removeСonsumption(consumption: Сonsumption) {
        _сonsumption.remove(consumption)
    }

    fun getAllСonsumption(): List<Сonsumption> {
        return _сonsumption
    }

    fun getSum(): Int {
        var count = 0
        _сonsumption.forEach(){categorys ->
            count = count + categorys.sum
        }
        return count
    }

    fun filterCategory(category: Category) {
        selectedCategory = category
    }
}