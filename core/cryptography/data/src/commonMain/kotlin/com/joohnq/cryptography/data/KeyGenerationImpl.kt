package com.joohnq.cryptography.data

import com.joohnq.cryptography.domain.KeyGeneration
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.materials.key.KeyGenerator

class KeyGenerationImpl(private val keyGenerator: KeyGenerator<AES.GCM.Key>) : KeyGeneration {
    override suspend operator fun invoke(): AES.GCM.Key = keyGenerator.generateKey()
}