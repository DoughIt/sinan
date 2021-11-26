package com.doughit.sinan.model

data class CommonResponse<T>(
    val code: Int,
    val message: String,
    val data: T
)
