package com.xlwe.lectures

interface ServiceCallback {
    fun returnSuccess(data: JokeDTO)
    fun returnError(type: ErrorType)
}