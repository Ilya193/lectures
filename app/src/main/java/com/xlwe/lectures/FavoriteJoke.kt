package com.xlwe.lectures

class FavoriteJoke(text: String) : Joke(text) {
    override fun getIconResId() =
        R.drawable.ic_baseline_favorite
}