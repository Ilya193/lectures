package com.xlwe.lectures

data class JokeDTO(
    val joke: String
) {
    fun toJoke() = Joke(joke)
}