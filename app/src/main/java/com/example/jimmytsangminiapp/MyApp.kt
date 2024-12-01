package com.example.jimmytsangminiapp

import android.app.Application
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import com.example.jimmytsangminiapp.data.JokeRepository

class MyApp : Application() {

    private val client = HttpClient {
        install(ContentNegotiation) {
            gson()
        }
    }

    val jokeRepository by lazy {
        JokeRepository(client)
    }
}
