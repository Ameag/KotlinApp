package com.example.rashod.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.example.rashod.ui.theme.RashodTheme
import com.example.rashod.viewModel.CategoryViewModel
import androidx.navigation.compose.rememberNavController
import com.example.rashod.view.screen.AddCategory
import androidx.navigation.compose.composable
import com.example.rashod.view.screen.AddConsumption
import com.example.rashod.view.screen.ListConsumption
import com.example.rashod.viewModel.ConsumptionViewModel

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RashodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModelCategory: CategoryViewModel = CategoryViewModel()
                    val viewModelConsumption: ConsumptionViewModel = ConsumptionViewModel()
                    NavHost(navController = navController, startDestination = "listConsumption") {
                        composable("addCategory") {
                            AddCategory(viewModelCategory ,navController)
                        }
                        composable("listConsumption"){
                            ListConsumption(viewModelConsumption,viewModelCategory, navController)
                        }
                        composable("addСonsumption"){
                            AddConsumption(viewModelConsumption,viewModelCategory, navController)
                        }
                    }
                }
            }
        }
    }
}

