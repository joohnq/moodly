package com.joohnq.moodapp.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.entities.palette.MoodPalette
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.depressed
import moodapp.composeapp.generated.resources.happy
import moodapp.composeapp.generated.resources.neutral
import moodapp.composeapp.generated.resources.overjoyed
import moodapp.composeapp.generated.resources.sad
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class Mood(
    val id: String,
    val image: DrawableResource,
    val imageVector: ImageVector,
    val text: StringResource,
    val healthLevel: Int
) {
    data object Depressed :
        Mood(
            id = DEPRESSED,
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            healthLevel = 20
        )

    data object Sad : Mood(
        id = SAD,
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        healthLevel = 40
    )

    data object Neutral :
        Mood(
            id = NEUTRAL,
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = Res.string.neutral,
            healthLevel = 60
        )

    data object Happy : Mood(
        id = HAPPY,
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        healthLevel = 80
    )

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
                color = Colors.Green10,
                subColor = Colors.Green40,
                backgroundColor = Colors.Green50
            )

            Happy -> MoodPalette(
                faceBackgroundColor = Colors.Yellow40,
                faceColor = Colors.Yellow90,
                color = Colors.Yellow10,
                subColor = Colors.Yellow40,
                backgroundColor = Colors.Yellow50
            )

            Neutral -> MoodPalette(
                faceBackgroundColor = Colors.Brown60,
                faceColor = Colors.Brown90,
                color = Colors.Brown20,
                subColor = Colors.Brown40,
                backgroundColor = Colors.Brown70
            )

            Sad -> MoodPalette(
                faceBackgroundColor = Colors.Orange40,
                faceColor = Colors.Orange90,
                color = Colors.Orange10,
                subColor = Colors.Orange30,
                backgroundColor = Colors.Orange40
            )

            Depressed -> MoodPalette(
                faceBackgroundColor = Colors.Purple30,
                faceColor = Colors.Purple90,
                color = Colors.Purple10,
                subColor = Colors.Purple30,
                backgroundColor = Colors.Purple40
            )
        }
    }
}
