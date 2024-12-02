package com.example.jimmytsangminiapp.data

data class JokeResponse(
    val error: Boolean,
    val category: String,
    val type: String,
    val joke: String?, // For "single" type jokes
    val setup: String?, // For "twopart" type jokes
    val delivery: String?, // For "twopart" type jokes
    val safe: Boolean
)