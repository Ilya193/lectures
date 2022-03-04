package com.xlwe.lectures.app

import android.app.Application
import com.xlwe.lectures.model.BaseModel
import com.xlwe.lectures.presenter.BasePresenter

class App : Application() {
    lateinit var presenter: BasePresenter

    override fun onCreate() {
        super.onCreate()
        presenter = BasePresenter(BaseModel())
    }
}