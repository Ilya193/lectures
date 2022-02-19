package com.xlwe.lectures

interface CacheDataSource {
    fun addOrRemove(text: String, joke: JokeServerModel): Joke
}