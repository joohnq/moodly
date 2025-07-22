package com.joohnq.self_journal.api.use_case

import com.joohnq.freud_score.api.entity.FreudScore
import com.joohnq.mood.api.entity.Mood
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class CalculateSelfJournalFreudScoreUseCaseTest {
    private lateinit var useCase: CalculateSelfJournalFreudScoreUseCase

    @BeforeTest
    fun setUp() {
        useCase = CalculateSelfJournalFreudScoreUseCase()
    }

    @Test
    fun `test CalculateSelfJournalFreudScoreUseCase Healthy`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    SelfJournalRecord(
                        mood = Mood.Overjoyed,
                    ),
                    SelfJournalRecord(
                        mood = Mood.Happy,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Healthy(90))
        }

    @Test
    fun `test CalculateSelfJournalFreudScoreUseCase Happy`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    SelfJournalRecord(
                        mood = Mood.Happy,
                    ),
                    SelfJournalRecord(
                        mood = Mood.Neutral,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.MostlyHealthy(70))
        }

    @Test
    fun `test CalculateSelfJournalFreudScoreUseCase Stable`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    SelfJournalRecord(
                        mood = Mood.Neutral,
                    ),
                    SelfJournalRecord(
                        mood = Mood.Sad,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Stable(50))
        }

    @Test
    fun `test CalculateSelfJournalFreudScoreUseCase AtRisk`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    SelfJournalRecord(
                        mood = Mood.Sad,
                    ),
                    SelfJournalRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.AtRisk(30))
        }

    @Test
    fun `test CalculateSelfJournalFreudScoreUseCase Unhealthy`() =
        runBlocking {
            val res = useCase.invoke(
                listOf(
                    SelfJournalRecord(
                        mood = Mood.Depressed,
                    ),
                    SelfJournalRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )
            assertThat(res).isEqualTo(FreudScore.Unhealthy(20))
        }
}
