package com.joohnq.moodapp.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.entities.palette.MoodPalette
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.depressed
import moodapp.composeapp.generated.resources.happy
import moodapp.composeapp.generated.resources.neutral
import moodapp.composeapp.generated.resources.overjoyed
import moodapp.composeapp.generated.resources.sad
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class Mood(
    val id: String,
    @Contextual val image: DrawableResource,
    @Contextual val imageVector: ImageVector,
    @Contextual val text: StringResource,
    val healthLevel: Int
) {
    @Serializable
    data object Depressed :
        Mood(
            id = DEPRESSED,
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            healthLevel = 20
        )

    @Serializable
    data object Sad : Mood(
        id = SAD,
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        healthLevel = 40
    )

    @Serializable
    data object Neutral :
        Mood(
            id = NEUTRAL,
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = Res.string.neutral,
            healthLevel = 60
        )

    @Serializable
    data object Happy : Mood(
        id = HAPPY,
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        healthLevel = 80
    )

    @Serializable
    data object Overjoyed :
        Mood(
            id = OVERJOYED,
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = Res.string.overjoyed,
            healthLevel = 100
        )

    companion object {
        private const val DEPRESSED = "0"
        private const val SAD = "1"
        private const val NEUTRAL = "2"
        private const val HAPPY = "3"
        private const val OVERJOYED = "4"

        fun toValue(src: String): Mood = when (src) {
            DEPRESSED -> Depressed
            SAD -> Sad
            NEUTRAL -> Neutral
            HAPPY -> Happy
            OVERJOYED -> Overjoyed
            else -> throw IllegalArgumentException("Unknown mood: $src")
        }

        fun fromValue(mood: Mood?): String = mood?.id.toString()

        fun getAll(): List<Mood> = listOf(
            Depressed,
            Sad,
            Neutral,
            Happy,
            Overjoyed
        )

        fun getSaver(): Saver<Mood, String> = Saver(
            save = { fromValue(it) },
            restore = { toValue(it) }
        )

        fun getPalette(value: Mood): MoodPalette = when (value) {
            Overjoyed -> MoodPalette(
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
            )

            Happy -> MoodPalette(
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
            )

            Neutral -> MoodPalette(
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
            )

            Sad -> MoodPalette(
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
            )

            Depressed -> MoodPalette(
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
            )
        }
    }
}
