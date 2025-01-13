package com.joohnq.health_journal.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
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
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Overjoyed,
                    ),
                    HealthJournalRecord(
                        mood = Mood.Happy,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Healthy(90))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Happy`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Happy,
                    ),
                    HealthJournalRecord(
                        mood = Mood.Neutral,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.MostlyHealthy(70))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Stable`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Neutral,
                    ),
                    HealthJournalRecord(
                        mood = Mood.Sad,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Stable(50))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase AtRisk`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Sad,
                    ),
                    HealthJournalRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.AtRisk(30))
        }

    @Test
    fun `test CalculateHealthJournalFreudScoreUseCase Unhealthy`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    HealthJournalRecord(
                        mood = Mood.Depressed,
                    ),
                    HealthJournalRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Unhealthy(20))
        }
}
