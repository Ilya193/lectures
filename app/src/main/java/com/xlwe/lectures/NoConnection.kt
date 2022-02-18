package com.xlwe.lectures

class NoConnection(private val resourceManager: ResourceManager) : Error {
    override fun getMessage() =
        resourceManager.getString(R.string.no_connection)
}