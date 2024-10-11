package com.joohnq.moodapp.entities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import moodapp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import moodapp.composeapp.generated.resources.depressed
import moodapp.composeapp.generated.resources.sad
import moodapp.composeapp.generated.resources.neutral
import moodapp.composeapp.generated.resources.happy
import moodapp.composeapp.generated.resources.overjoyed

sealed class Mood(
    val image: DrawableResource,
    val imageVector: ImageVector,
    val text: StringResource,
    val rouletteBackgroundColor: Color,
    val rouletteFaceColor: Color,
    val backgroundColor: Color,
    val color: Color,
) {
    data object Depressed :
        Mood(
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            rouletteBackgroundColor = Colors.Purple30,
            rouletteFaceColor = Colors.Purple50,
            backgroundColor = Colors.Purple30,
            color = Colors.Purple80
        )

    data object Sad : Mood(
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        rouletteBackgroundColor = Colors.Orange40,
        rouletteFaceColor = Colors.Orange70,
        backgroundColor = Colors.Orange40,
        color = Colors.Orange80
    )

    data object Neutral :
        Mood(
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = Res.string.neutral,
            rouletteBackgroundColor = Colors.Brown60,
            rouletteFaceColor = Colors.Brown80,
            backgroundColor = Colors.Brown40,
            color = Colors.Brown80
        )

    data object Happy : Mood(
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        rouletteBackgroundColor = Colors.Yellow40,
        rouletteFaceColor = Colors.Yellow70,
        backgroundColor = Colors.Yellow40,
        color = Colors.Yellow80
    )

    data object Overjoyed :
        Mood(
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = Res.string.overjoyed,
            rouletteBackgroundColor = Colors.Green50,
            rouletteFaceColor = Colors.Green70,
            backgroundColor = Colors.Green50,
            color = Colors.Green80
        )
}

val moods = listOf(
    Mood.Overjoyed,
    Mood.Happy,
    Mood.Neutral,
    Mood.Sad,
    Mood.Depressed,
)