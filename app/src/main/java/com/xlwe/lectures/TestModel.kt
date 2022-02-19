package com.xlwe.lectures

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class TestModel(
    private val service: JokeService,
    private val resourceManager: ResourceManager
) : Model {
    private var callback: ResultCallback? = null
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)
    private var count = 0

    override fun getJoke() {
        Thread {
            when (count) {
                0 -> callback?.provideJoke(BaseJoke("baseText"))
                1 -> callback?.provideJoke(FavoriteJoke("favoriteText"))
                2 -> callback?.provideJoke(FailedJoke(serviceUnavailable.getMessage()))
            }

            count++

            if (count == 3)
                count = 0

        }.start()

        /*service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO> {
            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if (response.isSuccessful)
                    callback?.provideSuccess(response.body()!!.toJoke())
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if (t is UnknownHostException)
                    callback?.provideError(noConnection)
                else
                    callback?.provideError(serviceUnavailable)
            }
        })*/
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}