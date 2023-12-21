package com.example.rashod.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rashod.model.Joke
import com.example.rashod.network.jokeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun DisplayJoke() {
    val joke = remember { mutableStateOf<Joke?>(null) }
    val error = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            withContext(Dispatchers.IO) {
                joke.value = jokeApiService.getJoke()
            }
        } catch (e: Exception) {
            error.value = e.message
        }
    }

    if (joke.value != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Шутка:")
            Spacer(modifier = Modifier.height(16.dp))
            Text(joke.value!!.setup)
            Spacer(modifier = Modifier.height(8.dp))
            Text(joke.value!!.punchline)
        }
    } else if (error.value != null) {
        Text(error.value!!)
        println(error.value!!)
    } else {
        CircularProgressIndicator()
    }
}