package com.xlwe.lectures.presenter

import com.xlwe.lectures.model.Model

interface Presenter {

    fun setFullName(fullName: String)
    fun setEmail(email: String)
    fun attachView(view: Model.ResultCallback)
    fun onDestroy()

}