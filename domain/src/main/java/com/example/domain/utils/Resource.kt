package com.example.domain.utils

data class Resource <out T>(val status: Status, val data: T? = null, val error: AppError? = null) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: AppError?, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, error)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    fun isSuccess(): Boolean = status == Status.SUCCESS

    fun isLoading(): Boolean = status == Status.LOADING
}