package com.xlwe.lectures

interface ResultCallback {
    fun provideSuccess(data: Joke)
    fun provideError(error: Error)
}