package com.example.rashod.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rashod.viewModel.CategoryViewModel

@ExperimentalMaterial3Api
@Composable
fun AddCategory(viewModel: CategoryViewModel,  navController: NavHostController, modifier: Modifier = Modifier) {
    var category by remember { mutableStateOf("")}
    Column( modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(
            onClick = { navController.navigate("listConsumption") },
            modifier = Modifier.padding(16.dp)
        ){
            Text(text = "Назад")
        }
        TextField(
            value = category,
            onValueChange = { category = it },
            placeholder = {
                Text(
                    "Категория",
                )
            }
        )
        Button(
            onClick = {
                viewModel.addCategory(category)
                navController.navigate("listConsumption")
            },
            modifier = Modifier.padding(16.dp),
            enabled = category.isNotBlank()
        ) {
            Text(text = "Сохранить")
        }
    }
}