package com.xlwe.lectures

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

class Repository {
    private val timer = Timer()

    var count = 0

    fun getCount(resultCallback: ResultCallback<Resource>) {

        resultCallback.provideInfo(Resource.Loading())
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                count += 10
                resultCallback.provideInfo(Resource.Success(count.toString()))
                Log.d("attadag", "count +=10")
            }
        }, 1000, 1000)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                timer.cancel()
            }
        }, 3010)
    }
}