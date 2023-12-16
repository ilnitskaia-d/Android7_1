package com.example.android7.ui

sealed class UIState<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Loading<T>(): UIState<T>()
    class Empty<T>(): UIState<T>()
    class Success<T>(data: T?): UIState<T>()
    class Error<T>(message: String?, code: Int?): UIState<T>()

}