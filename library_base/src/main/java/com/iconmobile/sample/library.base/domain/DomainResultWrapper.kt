package com.iconmobile.sample.library.base.domain

sealed class DomainResultWrapper<out T> {
    data class Success<out T>(val value: T): DomainResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: DomainErrorResponse? = null): DomainResultWrapper<Nothing>()
    data class NetworkError(val message: String): DomainResultWrapper<Nothing>()
}