package com.xlwe.lectures

interface ServiceCallback {
    fun returnSuccess(data: JokeServerModel)
    fun returnError(type: ErrorType)
}