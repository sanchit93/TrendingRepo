package com.example.data.model

data class ApiResponse<T>(
    val message: String?,
    val error: String?,
    val data: T?
)