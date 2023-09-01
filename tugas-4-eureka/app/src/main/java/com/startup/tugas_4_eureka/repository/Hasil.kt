package com.startup.tugas_4_eureka.repository

sealed class Hasil<out R> private constructor() {
    data class Success<out T> (val data: T) : Hasil<T>()
    data class Error(val msg: String) : Hasil<Nothing>()
    object Empty : Hasil<Nothing>()
    object Loading : Hasil<Nothing>()
}
