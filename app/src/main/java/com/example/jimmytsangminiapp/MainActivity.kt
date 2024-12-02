package com.example.jimmytsangminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jimmytsangminiapp.ui.CategoriesScreen
import com.example.jimmytsangminiapp.ui.FavoritesScreen
import com.example.jimmytsangminiapp.ui.RandomJokeScreen
import com.example.jimmytsangminiapp.ui.state.JokeState
import com.example.jimmytsangminiapp.ui.theme.JimmyTsangMiniAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val jokeRepository = (application as MyApp).jokeRepository
        val jokeState = JokeState(jokeRepository)

        // Trigger the joke fetching on app start
        lifecycleScope.launch {
            jokeState.getJoke()
        }

        setContent {
            JimmyTsangMiniAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopBar(navController)
                    },
                    content = { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "random_joke",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("random_joke") { RandomJokeScreen(jokeState, navController) }
                            composable("favorites") { FavoritesScreen(jokeState) }
                            composable("categories") { CategoriesScreen(jokeState) }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Jokes App") },
        actions = {
            IconButton(onClick = { navController.navigate("favorites") }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorites")
            }
            IconButton(onClick = { navController.navigate("categories") }) {
                Icon(Icons.Filled.Search, contentDescription = "Categories")
            }
            IconButton(onClick = { navController.navigate("random_joke") }) {
                Icon(Icons.Filled.Share, contentDescription = "Random Joke")
            }
        }
    )
}
