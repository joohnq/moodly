package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.repository.StatsRepository
import kotlin.test.BeforeTest

class AddStatsUseCaseTest {
    private lateinit var useCase: AddStatsUseCase
    private lateinit var repository: StatsRepository

    @BeforeTest
    fun setUp() {
        repository = StatsRepositoryFake()
        useCase = AddStatsUseCase(repository)
    }
}