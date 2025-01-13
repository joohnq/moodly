package com.joohnq.mood.domain.use_case

import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class CalculateStatsFreudScoreUseCaseTest {
    private var useCase: CalculateStatsFreudScoreUseCase = CalculateStatsFreudScoreUseCase()

    @Test
    fun `testing CalculateStatsFreudScoreUseCase Healthy`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                listOf(
                    StatsRecord(
                        mood = Mood.Overjoyed,
                    ),
                    StatsRecord(
                        mood = Mood.Happy,
                    ),
                )
            )

            //THEN
            assertThat(res).isEqualTo(FreudScore.Healthy(90))
        }

    @Test
    fun `testing CalculateStatsFreudScoreUseCase MostlyHealthy`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                listOf(
                    StatsRecord(
                        mood = Mood.Happy,
                    ),
                    StatsRecord(
                        mood = Mood.Neutral,
                    ),
                )
            )

            //THEN
            assertThat(res).isEqualTo(FreudScore.MostlyHealthy(70))
        }

    @Test
    fun `testing CalculateStatsFreudScoreUseCase Stable`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                listOf(
                    StatsRecord(
                        mood = Mood.Neutral,
                    ),
                    StatsRecord(
                        mood = Mood.Sad,
                    ),
                )
            )

            //THEN
            assertThat(res).isEqualTo(FreudScore.Stable(50))
        }

    @Test
    fun `testing CalculateStatsFreudScoreUseCase AtRisk`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                listOf(
                    StatsRecord(
                        mood = Mood.Sad,
                    ),
                    StatsRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )

            //THEN
            assertThat(res).isEqualTo(FreudScore.AtRisk(30))
        }

    @Test
    fun `testing CalculateStatsFreudScoreUseCase Unhealthy`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                listOf(
                    StatsRecord(
                        mood = Mood.Depressed,
                    ),
                    StatsRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )

            //THEN
            assertThat(res).isEqualTo(FreudScore.Unhealthy(20))
        }
}