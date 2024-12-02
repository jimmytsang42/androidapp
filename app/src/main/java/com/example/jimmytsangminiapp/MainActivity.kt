package com.example.jimmytsangminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jimmytsangminiapp.ui.CategoriesScreen
import com.example.jimmytsangminiapp.ui.FavoritesScreen
import com.example.jimmytsangminiapp.ui.RandomJokeScreen
import com.example.jimmytsangminiapp.ui.state.JokeState
import com.example.jimmytsangminiapp.ui.TopBar
import com.example.jimmytsangminiapp.ui.BottomBar
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
                        TopBar() // Just the title
                    },
                    bottomBar = {
                        BottomBar(navController) // Bottom bar with icons and text
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
