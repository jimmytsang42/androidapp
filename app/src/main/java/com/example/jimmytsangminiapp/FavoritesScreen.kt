package com.example.jimmytsangminiapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            itemsIndexed(jokeState.favorites) { index, joke ->
                // Box for each favorite joke to make it visually distinct
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        // Dynamically label each favorite with an index
                        Text(text = "Favorited Joke ${index + 1}:", fontWeight = FontWeight.Bold)

                        // Display the joke based on its type
                        if (joke.type == "twopart") {
                            // For two-part jokes, don't label setup and delivery
                            Text(text = joke.setup.orEmpty())
                            Text(text = joke.delivery.orEmpty())
                        } else {
                            // For one-part jokes, don't bold the text
                            Text(text = joke.joke.orEmpty(), fontWeight = FontWeight.Normal)
                        }

                        // Remove button for the favorites list
                        IconButton(onClick = {
                            jokeState.removeFavorite(joke)
                        }) {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Remove from favorites",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }
}

