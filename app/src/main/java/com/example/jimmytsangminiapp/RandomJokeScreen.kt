package com.example.jimmytsangminiapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // Center the content in the middle of the screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        joke?.let {
            // Grey square containing the joke and button
            Box(
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                    Text(text = "Category: ${it.category}")

                    if (it.type == "twopart") {
                        Text(text = it.setup.orEmpty())
                        Text(text = it.delivery.orEmpty())
                    } else {
                        Text(text = it.joke.orEmpty())
                    }

                    // Favorite button
                    IconButton(onClick = { jokeState.toggleFavorite(it) }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favorite",
                            tint = if (jokeState.isFavorite(it)) Color.Red else Color.Gray
                        )
                    }

                    // "Next Joke" button
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                jokeState.getJoke()
                            }
                        },
                        colors = buttonColors(
                            Color(0xFFde5100) // Orange color
                        )
                    ) {
                        Text("Next Joke")
                    }
                }
            }
        }
    }
}
