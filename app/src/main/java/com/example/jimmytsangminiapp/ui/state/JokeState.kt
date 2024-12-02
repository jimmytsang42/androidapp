package com.example.jimmytsangminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.jimmytsangminiapp.data.JokeRepository
import com.example.jimmytsangminiapp.data.JokeResponse

class JokeState(private val jokeRepository: JokeRepository) {

    var joke = mutableStateOf<JokeResponse?>(null)
    var favorites = mutableStateListOf<JokeResponse>()

    // Fetch jokes based on category
    suspend fun getJoke(category: String? = null) {
        joke.value = jokeRepository.getJokes(category)
    }

    fun toggleFavorite(joke: JokeResponse) {
        if (favorites.contains(joke)) {
            favorites.remove(joke)
        } else {
            favorites.add(joke)
        }
    }

    fun isFavorite(joke: JokeResponse): Boolean {
        return favorites.contains(joke)
    }
}
