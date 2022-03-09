package com.xlwe.lectures

interface ResultCallback<T> {
    fun provideInfo(data: T)
}