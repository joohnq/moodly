package com.joohnq.moodapp.model.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
    val backgroundColor: Color,
    val color: Color,
    val healthLevel: Int
) {
    data object Depressed :
        Mood(
            id = DEPRESSED,
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            backgroundColor = Colors.Purple30,
            color = Colors.Purple90,
            healthLevel = 20
        )

    data object Sad : Mood(
        id = SAD,
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        backgroundColor = Colors.Orange40,
        color = Colors.Orange90,
        healthLevel = 40
    )

    data object Neutral :
        Mood(
            id = NEUTRAL,
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = Res.string.neutral,
            backgroundColor = Colors.Brown60,
            color = Colors.Brown90,
            healthLevel = 60
        )

    data object Happy : Mood(
        id = HAPPY,
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        backgroundColor = Colors.Yellow40,
        color = Colors.Yellow90,
        healthLevel = 80
    )

    data object Overjoyed :
        Mood(
            id = OVERJOYED,
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = Res.string.overjoyed,
            backgroundColor = Colors.Green50,
            color = Colors.Green90,
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
    }
}
