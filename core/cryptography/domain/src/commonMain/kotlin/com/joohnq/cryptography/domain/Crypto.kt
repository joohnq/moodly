package com.joohnq.cryptography.domain

interface Crypto {
    suspend fun encrypt(bytes: ByteArray): ByteArray
    suspend fun decrypt(bytes: ByteArray): ByteArray
}