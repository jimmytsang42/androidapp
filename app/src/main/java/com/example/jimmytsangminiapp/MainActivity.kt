package com.example.jimmytsangminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import com.example.jimmytsangminiapp.ui.state.JokeState
import com.example.jimmytsangminiapp.MyApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val jokeRepository = (application as MyApp).jokeRepository

        setContent {
            val jokeState = remember { JokeState(jokeRepository) }

            LaunchedEffect(jokeState) {
                jokeState.getJoke()
            }

            val joke = jokeState.joke.value

            Column {
                joke?.let {
                    Text(text = "Category: ${it.category}", fontSize = 20.sp)

                    if (it.type == "single") {
                        Text(text = it.joke.orEmpty(), fontSize = 30.sp)
                    } else if (it.type == "twopart") {
                        Text(text = it.setup.orEmpty(), fontSize = 25.sp)
                        Text(text = it.delivery.orEmpty(), fontSize = 25.sp)
                    }
                }
            }
        }
    }
}
