package com.joohnq.domain.use_case

class OrganizeStatRangeUseCase {
    operator fun invoke(input: List<Double>): List<Double> =
        if (input.size < 8) listOf(0.0) + input + 0.0 else input
}
