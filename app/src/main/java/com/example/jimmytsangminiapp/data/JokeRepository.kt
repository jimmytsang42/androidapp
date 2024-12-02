package com.example.jimmytsangminiapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class JokeRepository(private val client: HttpClient) {
    suspend fun getJokes(category: String? = null): JokeResponse {
        // Use the format function to get the correct URL
        val url = EndPoints.JOKE_API.format(category)

        val response = client.get(url)
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, JokeResponse::class.java)
    }
}
