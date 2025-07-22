package com.joohnq.mood.api.use_case

import com.joohnq.freud_score.api.entity.FreudScore
import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.MoodRecord
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
                    MoodRecord(
                        mood = Mood.Overjoyed,
                    ),
                    MoodRecord(
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
                    MoodRecord(
                        mood = Mood.Happy,
                    ),
                    MoodRecord(
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
                    MoodRecord(
                        mood = Mood.Neutral,
                    ),
                    MoodRecord(
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
                    MoodRecord(
                        mood = Mood.Sad,
                    ),
                    MoodRecord(
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
                    MoodRecord(
                        mood = Mood.Depressed,
                    ),
                    MoodRecord(
                        mood = Mood.Depressed,
                    ),
                )
            )

            //THEN
            assertThat(res).isEqualTo(FreudScore.Unhealthy(20))
        }
}