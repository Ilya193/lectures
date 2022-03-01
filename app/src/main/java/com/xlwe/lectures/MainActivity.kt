package com.xlwe.lectures

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xlwe.lectures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = ComponentName(this, MyJobService::class.java)
        val info = JobInfo.Builder(MyJobService.JOB_ID, name)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .build()

        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.schedule(info)
    }
}
