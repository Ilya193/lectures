package com.xlwe.lectures

interface Model {
    fun getJoke()
    fun init(callback: ResultCallback)
    fun clear()
}