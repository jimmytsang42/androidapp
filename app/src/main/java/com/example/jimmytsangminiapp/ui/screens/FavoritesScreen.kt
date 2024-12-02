package com.example.jimmytsangminiapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

@Composable
fun FavoritesScreen(jokeState: JokeState) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        if (jokeState.favorites.isEmpty()) {
            item {
                Text(text = "No favorites yet.")
            }
        } else {
            itemsIndexed(jokeState.favorites) { index, joke ->
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(text = "Favorited Joke ${index + 1}:", fontWeight = FontWeight.Bold)

                        if (joke.type == "twopart") {
                            Text(text = "Category: ${joke.category}")
                            Text(text = joke.setup.orEmpty())
                            Text(text = joke.delivery.orEmpty())
                        } else {
                            Text(text = "Category: ${joke.category}")
                            Text(text = joke.joke.orEmpty(), fontWeight = FontWeight.Normal)
                        }

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
