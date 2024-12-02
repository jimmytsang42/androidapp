package com.example.jimmytsangminiapp.data

enum class EndPoints(val url: String) {
    BASE_URL("https://v2.jokeapi.dev/joke/"),
    CATEGORIES("Programming,Miscellaneous,Pun,Spooky,Christmas"),
    JOKE_API("${BASE_URL.url}%s?blacklistFlags=nsfw,religious,political,racist,sexist,explicit");

    fun format(category: String? = null): String {
        return if (category != null) {
            JOKE_API.url.format(category)
        } else {
            // Default URL
            "${BASE_URL.url}${CATEGORIES.url}?blacklistFlags=nsfw,religious,political,racist,sexist,explicit"
        }
    }
}
