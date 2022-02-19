package com.xlwe.lectures

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}