package com.example.jimmytsangminiapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jimmytsangminiapp.ui.state.JokeState
import kotlinx.coroutines.launch

@Composable
fun RandomJokeScreen(jokeState: JokeState, navController: NavController) {
    val joke = jokeState.joke.value
    val coroutineScope = rememberCoroutineScope()

    // Only load a new joke if one is not already loaded
    LaunchedEffect(joke == null) {
        if (joke == null) {
            jokeState.getJoke()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        joke?.let {
            Text(text = "Category: ${it.category}")

            if (it.type == "twopart") {
                Text(text = it.setup.orEmpty())
                Text(text = it.delivery.orEmpty())
            } else {
                Text(text = it.joke.orEmpty())
            }

            IconButton(onClick = { jokeState.toggleFavorite(it) }) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (jokeState.isFavorite(it)) androidx.compose.ui.graphics.Color.Red else androidx.compose.ui.graphics.Color.Gray
                )
            }

            Button(onClick = {
                coroutineScope.launch {
                    jokeState.getJoke()
                }
            }) {
                Text("Next Joke")
            }
        }
    }
}
