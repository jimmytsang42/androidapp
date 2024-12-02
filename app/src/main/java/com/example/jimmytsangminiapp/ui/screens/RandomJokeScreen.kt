package com.example.jimmytsangminiapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

                    Text(
                        text = "Category: ${it.category}",
                        style = TextStyle(fontSize = 31.sp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (it.type == "twopart") {
                        Text(
                            text = it.setup.orEmpty(),
                            modifier = Modifier.align(Alignment.Start),
                            style = TextStyle(fontSize = 26.sp)
                        )
                        Text(
                            text = it.delivery.orEmpty(),
                            modifier = Modifier.align(Alignment.Start),
                            style = TextStyle(fontSize = 26.sp)
                        )
                    } else {
                        Text(
                            text = it.joke.orEmpty(),
                            modifier = Modifier.align(Alignment.Start),
                            style = TextStyle(fontSize = 26.sp)
                        )
                    }

                    IconButton(onClick = { jokeState.toggleFavorite(it) }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favorite",
                            tint = if (jokeState.isFavorite(it)) Color.Red else Color.Gray,
                            modifier = Modifier.size(31.dp)
                        )
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                jokeState.getJoke()
                            }
                        },
                        colors = buttonColors(Color(0xFFde5100)),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text("Next Joke", style = TextStyle(fontSize = 30.sp))
                    }
                }
            }
        }
    }
}
