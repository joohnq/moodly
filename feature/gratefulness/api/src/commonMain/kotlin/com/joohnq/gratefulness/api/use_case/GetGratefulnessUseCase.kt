package com.joohnq.gratefulness.api.use_case

import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.repository.GratefulnessRepository
import kotlinx.coroutines.flow.Flow

class GetGratefulnessUseCase(
    private val repository: GratefulnessRepository,
) {
    operator fun invoke(): Flow<List<Gratefulness>> = repository.observe()
}
