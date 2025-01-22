package com.joohnq.cryptography.data

import com.joohnq.cryptography.domain.Crypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class CryptoImpl(private val cypher: CryptoCypher) :
    Crypto {
    override suspend fun encrypt(bytes: ByteArray): ByteArray = withContext(Dispatchers.IO) {
        val cypher = cypher.init()
        cypher.encrypt(bytes)
    }

    override suspend fun decrypt(bytes: ByteArray): ByteArray = withContext(Dispatchers.IO) {
        val cypher = cypher.init()
        cypher.decrypt(bytes)
    }
}