package com.joohnq.mood.ui

import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.mood.domain.MoodProperties
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodPalette
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.depressed
import com.joohnq.shared.ui.happy
import com.joohnq.shared.ui.neutral
import com.joohnq.shared.ui.overjoyed
import com.joohnq.shared.ui.sad
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class MoodResource(
    override val id: Int,
    val image: DrawableResource,
    val imageVector: ImageVector,
    val text: StringResource,
    override val healthLevel: Int,
    val palette: MoodPalette,
) : MoodProperties {
    data object Depressed :
        MoodResource(
            id = DEPRESSED,
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            healthLevel = 20,
            palette = MoodPalette(
                faceBackgroundColor = Colors.Purple30,
                faceColor = Colors.Purple90,
                backgroundColor = Colors.Purple10,
                subColor = Colors.Purple30,
                color = Colors.Purple40,
                moodScreenBackgroundColor = Colors.Purple30,
                moodScreenInactiveColor = Colors.Purple50,
                moodScreenTraceColor = Colors.Purple40,
                moodScreenMoodFaceBackgroundColor = Colors.Purple20,
                moodScreenMoodFaceColor = Colors.Purple80,
                barColor = Colors.Purple30,
                barFaceColor = Colors.Purple50,
            )
        )

    data object Sad : MoodResource(
        id = SAD,
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        healthLevel = 40,
        palette = MoodPalette(
            faceBackgroundColor = Colors.Orange40,
            faceColor = Colors.Orange90,
            backgroundColor = Colors.Orange10,
            subColor = Colors.Orange30,
            color = Colors.Orange40,
            moodScreenBackgroundColor = Colors.Orange40,
            moodScreenInactiveColor = Colors.Orange60,
            moodScreenTraceColor = Colors.Orange60,
            moodScreenMoodFaceBackgroundColor = Colors.Orange30,
            moodScreenMoodFaceColor = Colors.Orange80,
            barColor = Colors.Orange40,
            barFaceColor = Colors.Orange60,
        )
    )

    data object Neutral :
        MoodResource(
            NEUTRAL,
            Drawables.Mood.Neutral,
            Drawables.Mood.NeutralVectorPainter,
            Res.string.neutral,
            60,
            MoodPalette(
                faceBackgroundColor = Colors.Brown60,
                faceColor = Colors.Brown90,
                backgroundColor = Colors.Brown20,
                subColor = Colors.Brown40,
                color = Colors.Brown70,
                moodScreenBackgroundColor = Colors.Brown60,
                moodScreenInactiveColor = Colors.Brown80,
                moodScreenTraceColor = Colors.Brown80,
                moodScreenMoodFaceBackgroundColor = Colors.Brown40,
                moodScreenMoodFaceColor = Colors.Brown80,
                barColor = Colors.Brown50,
                barFaceColor = Colors.Brown70,
            )
        )

    data object Happy : MoodResource(
        id = HAPPY,
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        healthLevel = 80,
        palette = MoodPalette(
            faceBackgroundColor = Colors.Yellow40,
            faceColor = Colors.Yellow90,
            backgroundColor = Colors.Yellow10,
            subColor = Colors.Yellow40,
            color = Colors.Yellow50,
            moodScreenBackgroundColor = Colors.Yellow40,
            moodScreenInactiveColor = Colors.Yellow60,
            moodScreenTraceColor = Colors.Yellow60,
            moodScreenMoodFaceBackgroundColor = Colors.Yellow20,
            moodScreenMoodFaceColor = Colors.Yellow80,
            barColor = Colors.Yellow40,
            barFaceColor = Colors.Yellow60,
        )
    )

    data object Overjoyed :
        MoodResource(
            id = OVERJOYED,
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = Res.string.overjoyed,
            healthLevel = 100,
            palette = MoodPalette(
                faceBackgroundColor = Colors.Green50,
                faceColor = Colors.Green90,
                backgroundColor = Colors.Green10,
                subColor = Colors.Green40,
                color = Colors.Green50,
                moodScreenBackgroundColor = Colors.Green50,
                moodScreenInactiveColor = Colors.Green50,
                moodScreenTraceColor = Colors.Green50,
                moodScreenMoodFaceBackgroundColor = Colors.Green30,
                moodScreenMoodFaceColor = Colors.Green80,
                barColor = Colors.Green50,
                barFaceColor = Colors.Green70,
            )
        )

    companion object {
        private const val DEPRESSED = 0
        private const val SAD = 1
        private const val NEUTRAL = 2
        private const val HAPPY = 3
        private const val OVERJOYED = 4

        fun toValue(src: Int): MoodResource = when (src) {
            DEPRESSED -> Depressed
            SAD -> Sad
            NEUTRAL -> Neutral
            HAPPY -> Happy
            OVERJOYED -> Overjoyed
            else -> throw IllegalArgumentException("Unknown mood: $src")
        }

        fun MoodResource.fromValue(): Int = this.id

        fun getAll(): List<MoodResource> = listOf(
            Depressed,
            Sad,
            Neutral,
            Happy,
            Overjoyed
        )

        fun Mood.toResource(): MoodResource =
            when (this) {
                is Mood.Depressed -> Depressed
                is Mood.Sad -> Sad
                is Mood.Neutral -> Neutral
                is Mood.Happy -> Happy
                is Mood.Overjoyed -> Overjoyed
            }

        fun List<Mood>.toResource(): List<MoodResource> = map { it.toResource() }

        fun MoodResource.toDomain(): Mood =
            when (this) {
                Depressed -> Mood.Depressed
                Sad -> Mood.Sad
                Neutral -> Mood.Neutral
                Happy -> Mood.Happy
                Overjoyed -> Mood.Overjoyed
            }

        fun MoodResource.toSleepQuality(): SleepQuality =
            when (this) {
                Depressed -> SleepQuality.Worst
                Sad -> SleepQuality.Poor
                Neutral -> SleepQuality.Fair
                Happy -> SleepQuality.Good
                Overjoyed -> SleepQuality.Excellent
            }
    }
}