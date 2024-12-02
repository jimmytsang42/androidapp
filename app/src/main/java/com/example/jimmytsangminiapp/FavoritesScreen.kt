package com.example.jimmytsangminiapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jimmytsangminiapp.ui.state.JokeState

@Composable
fun FavoritesScreen(jokeState: JokeState) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        // Observe favorites and update UI when it changes
        if (jokeState.favorites.isEmpty()) {
            item {
                Text(text = "No favorites yet.")
            }
        } else {
            items(jokeState.favorites) { joke ->
                Text(text = joke.joke.orEmpty())
            }
        }
    }
}

