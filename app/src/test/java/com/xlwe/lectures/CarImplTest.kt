package com.xlwe.lectures

import org.junit.Assert
import org.junit.Test

class CarImplTest {
    @Test
    fun speed_100() {
        val car = Car()
        car.run()
        Assert.assertEquals(100, car.speed)
    }

    @Test(expected = IllegalStateException::class)
    fun car_swim_exception() {
        val car = Car()
        car.swim()
    }
}

class Car {
    var speed = 0

    fun run() {
        speed = 100
    }

    fun swim() {
        throw IllegalStateException("Car can't swim")
    }
}