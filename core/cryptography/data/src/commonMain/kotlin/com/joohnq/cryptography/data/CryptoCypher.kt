package com.joohnq.cryptography.data

import com.joohnq.cryptography.domain.KeyGeneration
import dev.whyoleg.cryptography.algorithms.AES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class CryptoCypher(private val keyGeneration: KeyGeneration) {
    suspend fun init(): AES.IvAuthenticatedCipher = withContext(Dispatchers.IO) {
        val key: AES.GCM.Key = keyGeneration.invoke()
        key.cipher()
    }
}