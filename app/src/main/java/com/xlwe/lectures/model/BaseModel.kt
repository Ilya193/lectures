package com.xlwe.lectures.model

import java.util.*

class BaseModel : Model {

    private var fullName = ""
    private var email = ""
    private var onResultCallback: Model.ResultCallback? = null

    override fun init(onResultCallback: Model.ResultCallback) {
        this.onResultCallback = onResultCallback
    }

    override fun setFullName(fullName: String) {
        this.fullName = fullName
        Timer().schedule(object : TimerTask() {
            override fun run() {
                onResultCallback?.provideInfo("Success")
            }
        }, 5000)
    }

    override fun setEmail(email: String) {
        this.email = email
        Timer().schedule(object : TimerTask() {
            override fun run() {
                onResultCallback?.provideInfo("Success")
            }
        }, 5000)
    }

}