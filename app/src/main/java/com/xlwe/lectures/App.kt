package com.xlwe.lectures

import android.app.Application

class App : Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = ViewModel(Model(CacheDataSource(this)))
    }
}