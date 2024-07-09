package com.findyourbook.utils

sealed class RequestResult<T>(val data: T?) {
    class Success<T>(data: T) : RequestResult<T>(data)

    class Error<T>(data: T? = null, val error: Throwable) : RequestResult<T>(data)

    class InProgress<T>(data: T? = null) : RequestResult<T>(data)
}
