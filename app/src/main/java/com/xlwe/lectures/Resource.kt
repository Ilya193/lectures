package com.xlwe.lectures

sealed class Resource(
    val data: String?
) {

    class Loading : Resource(null)
    class Success(data: String) : Resource(data)

}