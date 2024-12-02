package com.example.jimmytsangminiapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jimmytsangminiapp.ui.state.JokeState
import kotlinx.coroutines.launch

@Composable
fun RandomJokeScreen(jokeState: JokeState) {
    val joke = jokeState.joke.value
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(joke == null) {
        if (joke == null) {
            jokeState.getJoke()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        joke?.let {
            Box(
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Category: ${it.category}")

                    if (it.type == "twopart") {
                        Text(text = it.setup.orEmpty(), modifier = Modifier.align(Alignment.Start))
                        Text(text = it.delivery.orEmpty(), modifier = Modifier.align(Alignment.Start))
                    } else {
                        Text(text = it.joke.orEmpty(), modifier = Modifier.align(Alignment.Start))
                    }

                    IconButton(onClick = { jokeState.toggleFavorite(it) }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favorite",
                            tint = if (jokeState.isFavorite(it)) Color.Red else Color.Gray
                        )
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                jokeState.getJoke()
                            }
                        },
                        colors = buttonColors(
                            Color(0xFFde5100)
                        )
                    ) {
                        Text("Next Joke")
                    }
                }
            }
        }
    }
}
