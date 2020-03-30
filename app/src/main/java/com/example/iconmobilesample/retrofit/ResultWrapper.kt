package com.example.iconmobilesample.retrofit

import com.iconmobile.sample.library.base.domain.DomainErrorResponse
import com.iconmobile.sample.library.base.domain.DomainResultWrapper

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) :
        ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}

// extension function to convert data to domain result wrapper
fun <T> ResultWrapper<T>.toDomainResultWrapper(): DomainResultWrapper<T> = when (this) {
    is ResultWrapper.Success<T> -> DomainResultWrapper.Success(this.value)
    is ResultWrapper.GenericError -> DomainResultWrapper.GenericError(
        this.code,
        this.error?.toDomainError()
    )
    is ResultWrapper.NetworkError -> DomainResultWrapper.NetworkError("Network error occurred")
}

// extension function to convert data to domain error
fun ErrorResponse.toDomainError() = DomainErrorResponse(this.message)
