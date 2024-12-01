package com.example.jimmytsangminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.jimmytsangminiapp.data.JokeRepository
import com.example.jimmytsangminiapp.data.JokeResponse

class JokeState(private val jokeRepository: JokeRepository) {

    var joke = mutableStateOf<JokeResponse?>(null)

    suspend fun getJoke() {
        joke.value = jokeRepository.getJokes()
    }
}