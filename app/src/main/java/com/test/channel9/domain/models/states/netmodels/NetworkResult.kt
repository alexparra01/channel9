package com.test.channel9.domain.models.states.netmodels

sealed class NetworkResult< out T>(
    val data: T? = null,
    val message: String? = null
) {
    data class Success<out R>(val _data: R?): NetworkResult<R>(
        data = _data,
        message = null
    )
    data class Error(val _message: String): NetworkResult<Nothing>(
        data = null,
        message= _message
    )
}