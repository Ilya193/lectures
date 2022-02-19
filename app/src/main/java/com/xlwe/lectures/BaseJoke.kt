package com.xlwe.lectures

class BaseJoke(text: String) : Joke(text) {
    override fun getIconResId() =
        R.drawable.ic_favorite_border
}