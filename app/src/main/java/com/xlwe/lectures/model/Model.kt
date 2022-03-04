package com.xlwe.lectures.model

interface Model {

    fun setFullName(fullName: String)
    fun setEmail(email: String)
    fun init(onResultCallback: ResultCallback)

    interface ResultCallback {
        fun provideInfo(str: String)
    }

}