package com.xlwe.lectures

data class JokeServerModel(
    val joke: String
) {
    fun toJoke() = BaseJoke(joke)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(joke, this)
}