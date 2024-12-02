package com.example.jimmytsangminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.jimmytsangminiapp.data.JokeRepository
import com.example.jimmytsangminiapp.data.JokeResponse

// Refactor toggleFavorite function to ensure it works consistently
class JokeState(private val jokeRepository: JokeRepository) {

    var joke = mutableStateOf<JokeResponse?>(null)
    var favorites = mutableStateListOf<JokeResponse>()

    // Fetch jokes based on category
    suspend fun getJoke(category: String? = null) {
        joke.value = jokeRepository.getJokes(category)
    }

    // Toggle favorite functionality
    fun toggleFavorite(joke: JokeResponse) {
        // Using a mutable list to hold favorites and checking if the joke exists
        if (favorites.contains(joke)) {
            favorites.remove(joke)  // Remove if already favorited
        } else {
            favorites.add(joke)  // Add to favorites if not already there
        }
    }

    // Check if a joke is favorited
    fun isFavorite(joke: JokeResponse): Boolean {
        return favorites.contains(joke)
    }
}