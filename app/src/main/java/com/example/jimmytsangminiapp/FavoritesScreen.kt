package com.example.jimmytsangminiapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jimmytsangminiapp.ui.state.JokeState

// Favorite Screen with a better UI for displaying jokes
@Composable
fun FavoritesScreen(jokeState: JokeState) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        // Check if favorites are empty
        if (jokeState.favorites.isEmpty()) {
            item {
                Text(text = "No favorites yet.")
            }
        } else {
            items(jokeState.favorites) { joke ->
                // Box for each favorite joke to make it visually distinct
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(text = "Favorited Joke: ${joke.joke.orEmpty()}", fontWeight = FontWeight.Bold)
                        if (joke.type == "twopart") {
                            Text(text = "Setup: ${joke.setup.orEmpty()}")
                            Text(text = "Delivery: ${joke.delivery.orEmpty()}")
                        }
                    }
                }
            }
        }
    }
}

