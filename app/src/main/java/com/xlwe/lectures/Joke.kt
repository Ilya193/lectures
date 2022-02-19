package com.xlwe.lectures

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

abstract class Joke(private val text: String) {
    fun getJokeUi() = text

    @DrawableRes
    abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}