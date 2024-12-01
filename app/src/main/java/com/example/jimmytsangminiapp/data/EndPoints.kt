package com.example.jimmytsangminiapp.data

enum class EndPoints(var url:String) {
    JOKE_API("https://v2.jokeapi.dev/joke/Programming,Miscellaneous,Pun,Spooky,Christmas?blacklistFlags=nsfw,religious,political,racist,sexist,explicit");

    fun format(str: String?): String{
        if (str == null) return ""
        return url.format(str)
    }
}