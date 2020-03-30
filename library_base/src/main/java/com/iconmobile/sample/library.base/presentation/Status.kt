package com.iconmobile.sample.library.base.presentation

enum class Status {
    EMPTY_SUCCESS,
    RUNNING,
    SUCCESS,
    NETWORK_FAILED,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
        val status: Status,
        val msg: String? = null) {
    companion object {
        val EMPTY = NetworkState(Status.EMPTY_SUCCESS)
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun networkError(msg: String?) = NetworkState(Status.NETWORK_FAILED, msg)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}