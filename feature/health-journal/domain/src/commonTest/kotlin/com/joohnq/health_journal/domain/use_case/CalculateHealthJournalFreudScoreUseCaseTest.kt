package com.joohnq.health_journal.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CalculateHealthJournalFreudScoreUseCaseTest {
    private lateinit var useCase: CalculateHealthJournalFreudScoreUseCase

    @BeforeTest
    fun setUp() {
        useCase = CalculateHealthJournalFreudScoreUseCase()
    }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Healthy`() =
        runTest {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Overjoyed,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Healthy(100))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Happy`() =
        runTest {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Happy,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.MostlyHealthy(80))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Stable`() =
        runTest {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Neutral,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Stable(60))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase AtRisk`() =
        runTest {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Sad,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.AtRisk(40))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Unhealthy`() =
        runTest {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Unhealthy(20))
        }
}
