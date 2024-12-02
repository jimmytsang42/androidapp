package com.example.jimmytsangminiapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.jimmytsangminiapp.data.JokeResponse
import com.example.jimmytsangminiapp.ui.state.JokeState
import kotlinx.coroutines.launch

@Composable
fun CategoriesScreen(jokeState: JokeState) {
    val categories = listOf("Programming", "Miscellaneous", "Pun", "Spooky", "Christmas")
    val jokesByCategory = remember {
        mutableMapOf<String, MutableState<JokeResponse?>>().apply {
            categories.forEach { category -> this[category] = mutableStateOf(null) }
        }
    }
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        items(categories) { category ->
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {

                Button(
                    onClick = {
                        coroutineScope.launch {
                            jokeState.getJoke(category)
                            jokesByCategory[category]?.value = jokeState.joke.value
                        }
                    },
                    colors = buttonColors(
                        Color(0xFFde5100)
                    )
                ) {
                    Text(text = category)
                }

                jokesByCategory[category]?.value?.let { joke ->
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Column {
                            if (joke.type == "twopart") {
                                Text(text = joke.setup.orEmpty())
                                Text(text = joke.delivery.orEmpty())
                            } else {
                                Text(text = joke.joke.orEmpty())
                            }

                            IconButton(onClick = { jokeState.toggleFavorite(joke) }) {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Favorite",
                                    tint = if (jokeState.isFavorite(joke)) Color.Red else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
