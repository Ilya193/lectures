package com.xlwe.lectures

interface DataSource {
    fun saveInt(key: String, value: Int)
    fun getInt(key: String): Int
}