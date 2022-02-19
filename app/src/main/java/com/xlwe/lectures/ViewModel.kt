package com.xlwe.lectures

class ViewModel(private val model: Model) {
    private var callback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.callback = callback

        model.init(object : ResultCallback {
            override fun provideJoke(data: Joke) {
                callback.run {
                    provideText(data.getJokeUi())
                    provideIconRes(data.getIconResId())
                }
            }
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun chooseFavorites(favorites: Boolean) {

    }

    fun clear() {
        callback = null
        model.clear()
    }
}