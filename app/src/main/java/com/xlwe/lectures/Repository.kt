package com.xlwe.lectures

interface Repository {

    fun delete(position: Int)
    fun update(): List<Note>

}