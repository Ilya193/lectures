package com.xlwe.lectures.presenter

import com.xlwe.lectures.model.Model

class BasePresenter(
    private val model: Model
) : Presenter, Model.ResultCallback {

    private var view: Model.ResultCallback? = null

    init {
        model.init(this)
    }

    override fun setFullName(fullName: String) {
        model.setFullName(fullName)
    }


    override fun setEmail(email: String) {
        model.setEmail(email)
    }

    override fun attachView(view: Model.ResultCallback) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

    override fun provideInfo(str: String) {
        view?.provideInfo(str)
    }

}