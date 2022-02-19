package com.xlwe.lectures

interface JokeCloudCallback {
    fun provide(joke: JokeServerModel)
    fun fail(error: ErrorType)
}