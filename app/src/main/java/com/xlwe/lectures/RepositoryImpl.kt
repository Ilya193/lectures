package com.xlwe.lectures

import android.util.Log

class RepositoryImpl : Repository {
    private val numbers = mutableListOf<Note>()

    init {
        /*for (i in 0..50) {
            numbers.add("repository $i")
        }*/

        numbers.addAll(listOf(
            Note(1, "note"),
            Note(2, "note"),
            Note(3, "note"),
            Note(4, "asdfsadfsadfsdf"),
            Note(5, "agfgytiuyi")))
    }

    override fun delete(position: Int) {
        Log.d("attadag", "position $position")
        numbers.removeAt(position)
    }

    override fun update() = numbers
}