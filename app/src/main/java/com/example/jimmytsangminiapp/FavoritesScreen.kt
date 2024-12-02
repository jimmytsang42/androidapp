package com.example.jimmytsangminiapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jimmytsangminiapp.ui.state.JokeState

@Composable
fun FavoritesScreen(jokeState: JokeState) {
    Column(modifier = Modifier.padding(16.dp)) {
        jokeState.favorites.forEach { joke ->
            Text(text = joke.joke.orEmpty())
        }
    }
}
