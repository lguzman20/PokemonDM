package com.luisguzman.pokemondm.utils

sealed class State<out T>{
    class Loading<out T>: State<T>()
    data class Success<out T>(val metaData: T) : State<T>()
    data class Failure(val throwable: Throwable): State<Nothing>()
}