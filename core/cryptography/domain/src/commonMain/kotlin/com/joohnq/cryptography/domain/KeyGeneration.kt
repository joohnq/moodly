package com.joohnq.cryptography.domain

import dev.whyoleg.cryptography.algorithms.AES

interface KeyGeneration {
    suspend operator fun invoke(): AES.GCM.Key
}