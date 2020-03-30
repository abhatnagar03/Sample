package com.example.iconmobilesample.retrofit

import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return try {
        val response = withContext(Dispatchers.IO) { apiCall.invoke() }
        ResultWrapper.Success(response)
    } catch (throwable: Throwable) {
        withContext(Dispatchers.Main) {
            throwable.printStackTrace()
            Timber.e(throwable.cause, "Call error: ${throwable.localizedMessage}")
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                is IOException -> ResultWrapper.NetworkError
                else -> ResultWrapper.GenericError(null, null)
            }
        }
    }
}

fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}