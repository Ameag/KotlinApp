package com.example.rashod.viewModel

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rashod.model.Category
import com.example.rashod.model.Joke
import com.example.rashod.network.jokeApiService
import kotlinx.coroutines.launch
import java.io.IOException


class getJokeApi : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getJoke()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    private fun getJoke() {
        viewModelScope.launch {
            val listResult = jokeApiService.getJoke()
        }
    }
}

