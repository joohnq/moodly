package com.joohnq.moodapp.view.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
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

sealed class Mood(
    val id: String,
    val image: DrawableResource,
    val imageVector: ImageVector,
    val text: StringResource,
    val backgroundColor: Color,
    val color: Color,
    val healthLevel: Float
) {
    data object Depressed :
        Mood(
            id = DepressedId,
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            backgroundColor = Colors.Purple30,
            color = Colors.Purple90,
            healthLevel = 20f
        )

    data object Sad : Mood(
        id = SadId,
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        backgroundColor = Colors.Orange40,
        color = Colors.Orange90,
        healthLevel = 40f
    )

    data object Neutral :
        Mood(
            id = NeutralID,
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = Res.string.neutral,
            backgroundColor = Colors.Brown60,
            color = Colors.Brown90,
            healthLevel = 60f
        )

    data object Happy : Mood(
        id = HappyId,
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        backgroundColor = Colors.Yellow40,
        color = Colors.Yellow90,
        healthLevel = 80f
    )

    data object Overjoyed :
        Mood(
            id = OverjoyedId,
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = Res.string.overjoyed,
            backgroundColor = Colors.Green50,
            color = Colors.Green90,
            healthLevel = 100f
        )

    companion object {
        const val DepressedId = "0"
        const val SadId = "1"
        const val NeutralID = "2"
        const val HappyId = "3"
        const val OverjoyedId = "4"

        fun valueOf(src: String): Mood = when (src) {
            DepressedId -> Depressed
            SadId -> Sad
            NeutralID -> Neutral
            HappyId -> Happy
            OverjoyedId -> Overjoyed
            else -> throw IllegalArgumentException("Unknown mood: $src")
        }

        fun getAll(): List<Mood> = listOf(
            Depressed,
            Sad,
            Neutral,
            Happy,
            Overjoyed
        )
    }
}


val MoodSaver = Saver<Mood, String>(
    save = { opt -> opt.id },
    restore = { name -> Mood.valueOf(name) }
)
