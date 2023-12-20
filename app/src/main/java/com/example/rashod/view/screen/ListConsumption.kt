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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.rashod.viewModel.CategoryViewModel
import com.example.rashod.viewModel.ConsumptionViewModel

@Composable
fun ListConsumption(viewModel: ConsumptionViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectCategory by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Button(
            onClick = { navController.navigate("addCategory") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Добавить категорию")
        }
        Button(
            onClick = { navController.navigate("addСonsumption") },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Добавить расходы")
        }
        Box(
            modifier = Modifier
                .padding(top = 20.dp)
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
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
        ) {
            viewModel.category.forEach { categoryes ->
                DropdownMenuItem(text = {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Text(categoryes.category.category)
                    }
                }, onClick = {
                    viewModel.selectedCategory = categoryes.category
                    expanded = false
                })
            }
        }
        viewModel.category.filter { it.category == viewModel.selectedCategory }
            .forEach { categorys ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = categorys.sum.toString(),
                            color = Color.Black,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                        Text(
                            text = categorys.category.category,
                            color = Color.Black,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                        IconButton(
                            onClick = { viewModel.removeСonsumption(categorys) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
    }
}