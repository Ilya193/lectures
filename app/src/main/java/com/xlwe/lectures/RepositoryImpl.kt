package com.xlwe.lectures

class RepositoryImpl : Repository {
    private val numbers = mutableListOf<Int>()

    init {
        for (i in 0..10) {
            numbers.add(i)
        }
    }

    override fun delete(position: Int) {
        numbers.remove(position)
    }

    override fun update() = numbers
}