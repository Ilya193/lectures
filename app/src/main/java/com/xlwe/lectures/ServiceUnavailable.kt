package com.xlwe.lectures

class ServiceUnavailable(private val resourceManager: ResourceManager) : Error {
    override fun getMessage() =
        resourceManager.getString(R.string.service_unavailable)
}