package com.xlwe.lectures

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyJobService : JobService() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        Log.d("MyJobService", "onCreate")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        coroutineScope.launch {
            for (i in 0..10) {
                delay(1000)
                Log.d("MyJobService", "$i")
            }

            jobFinished(params, false)
        }

        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("MyJobService", "onStopJob")
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyJobService", "onDestroy")
    }

    companion object {
        const val JOB_ID = 1
    }
}