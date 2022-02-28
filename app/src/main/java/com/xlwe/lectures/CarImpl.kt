package com.xlwe.lectures

class CarImpl(private val isValid: Boolean) : Car {
    override fun start(): Boolean {
        return isValid
    }
}