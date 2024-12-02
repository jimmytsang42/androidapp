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
import com.example.jimmytsangminiapp.ui.screens.CategoriesScreen
import com.example.jimmytsangminiapp.ui.screens.FavoritesScreen
import com.example.jimmytsangminiapp.ui.screens.RandomJokeScreen
import com.example.jimmytsangminiapp.ui.state.JokeState
import com.example.jimmytsangminiapp.ui.components.TopBar
import com.example.jimmytsangminiapp.ui.components.BottomBar
import com.example.jimmytsangminiapp.ui.theme.JimmyTsangMiniAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val jokeRepository = (application as MyApp).jokeRepository
        val jokeState = JokeState(jokeRepository)

        lifecycleScope.launch {
            jokeState.getJoke()
        }

        setContent {
            JimmyTsangMiniAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopBar()
                    },
                    bottomBar = {
                        BottomBar(navController)
                    },
                    content = { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "random_joke",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("random_joke") { RandomJokeScreen(jokeState) }
                            composable("favorites") { FavoritesScreen(jokeState) }
                            composable("categories") { CategoriesScreen(jokeState) }
                        }
                    }
                )
            }
        }
    }
}
