package com.xlwe.lectures

class Person(private val car: Car) {
    fun drive(): Boolean {
        return car.start()
    }
}