package com.xlwe.lectures

class ViewModel(private val model: Model) {
    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback

        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) {
                callback.provideText(data.getJokeUi())
            }

            override fun provideError(error: Error) {
                callback.provideText(error.getMessage())
            }
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}