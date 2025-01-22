package com.joohnq.cryptography.domain

interface KeyManagement<T> {
    fun create(): T
    fun get(): T
}
