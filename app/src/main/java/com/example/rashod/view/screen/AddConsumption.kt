package com.example.rashod.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rashod.model.Category
import com.example.rashod.viewModel.CategoryViewModel
import com.example.rashod.viewModel.ConsumptionViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddConsumption(viewModelConsumption: ConsumptionViewModel, viewModelCategory: CategoryViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    val categories = viewModelCategory.getAllCategories()
    var expanded by remember { mutableStateOf(false) }
    var selectCategory by remember { mutableStateOf("") }
    var selectCategory2: Category? by remember { mutableStateOf(categories.firstOrNull()) }
    var amountInput by remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("listConsumption") },
        ) {
            Text(text = "Назад")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .background(
                        Color(
                            red = 233,
                            green = 224,
                            blue = 237
                        )
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    selectCategory.ifEmpty { "Выберите категорию" },
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(text = {
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Text(category.category)
                        }
                    }, onClick = {
                        selectCategory = category.category
                        selectCategory2 = category
                        expanded = false
                    })
                }
            }
        }
        TextField(
            value = amountInput,
            onValueChange = { newValue ->
                amountInput = newValue.filter { it.isDigit() || it == '.' }
            },
            label = { Text("Сумма") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            selectCategory2?.let {
                viewModelConsumption.addСonsumption(amountInput.toInt(), it)
                navController.navigate("listConsumption")
            }
        }) {
            Text(text = "Сохранить")
        }
    }
}

