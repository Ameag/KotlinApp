package com.example.rashod.view.screen

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rashod.model.Joke
import com.example.rashod.network.jokeApiService
import com.example.rashod.viewModel.CategoryViewModel
import com.example.rashod.viewModel.ConsumptionViewModel
import com.example.rashod.viewModel.getJokeApi
import kotlinx.coroutines.async

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun ListConsumption(viewModelConsumption: ConsumptionViewModel, viewModelCategory: CategoryViewModel, navController: NavHostController, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selectCategory by remember { mutableStateOf("") }
    val allCategory = viewModelCategory.getAllCategories()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.navigate("addCategory") },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = "Добавить категорию")
            }

            Button(
                onClick = { navController.navigate("addСonsumption") },
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = "Добавить расходы")
            }
        }
        Button(
            onClick = { viewModelConsumption.selectedCategory = null
                navController.navigate("listConsumption")},
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Сбросить выбор категории")
        }

        Text(
            text = "Всего потраченно: " + viewModelConsumption.getSum().toString(),
            fontSize = 20.sp
        )
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
            viewModelCategory.category.forEach { categoryes ->
                DropdownMenuItem(text = {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Text(categoryes.category)
                        IconButton(
                            onClick = { viewModelCategory.removeCategory(categoryes) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.Black
                            )
                        }

                    }
                }, onClick = {
                    viewModelConsumption.selectedCategory = categoryes
                    expanded = false
                })
            }
        }
        if (viewModelConsumption.selectedCategory == null) {
            viewModelConsumption.category.forEach() { categorys ->
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
                            onClick = { viewModelConsumption.removeСonsumption(categorys) },
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
        } else{
            viewModelConsumption.category.filter { it.category == viewModelConsumption.selectedCategory }
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
                                onClick = { viewModelConsumption.removeСonsumption(categorys) },
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
        Column ( verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start){
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                DisplayJoke()
            }
        }
    }
}