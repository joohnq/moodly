package com.joohnq.moodapp.view.entities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
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
            image = CustomDrawables.Mood.Depressed,
            imageVector = CustomDrawables.Mood.DepressedVectorPainter,
            text = Res.string.depressed,
            rouletteBackgroundColor = CustomColors.Purple30,
            rouletteFaceColor = CustomColors.Purple50,
            backgroundColor = CustomColors.Purple30,
            color = CustomColors.Purple80
        )

    data object Sad : Mood(
        image = CustomDrawables.Mood.Sad,
        imageVector = CustomDrawables.Mood.SadVectorPainter,
        text = Res.string.sad,
        rouletteBackgroundColor = CustomColors.Orange40,
        rouletteFaceColor = CustomColors.Orange70,
        backgroundColor = CustomColors.Orange40,
        color = CustomColors.Orange80
    )

    data object Neutral :
        Mood(
            image = CustomDrawables.Mood.Neutral,
            imageVector = CustomDrawables.Mood.NeutralVectorPainter,
            text = Res.string.neutral,
            rouletteBackgroundColor = CustomColors.Brown60,
            rouletteFaceColor = CustomColors.Brown80,
            backgroundColor = CustomColors.Brown40,
            color = CustomColors.Brown80
        )

    data object Happy : Mood(
        image = CustomDrawables.Mood.Happy,
        imageVector = CustomDrawables.Mood.HappyVectorPainter,
        text = Res.string.happy,
        rouletteBackgroundColor = CustomColors.Yellow40,
        rouletteFaceColor = CustomColors.Yellow70,
        backgroundColor = CustomColors.Yellow40,
        color = CustomColors.Yellow80
    )

    data object Overjoyed :
        Mood(
            image = CustomDrawables.Mood.Overjoyed,
            imageVector = CustomDrawables.Mood.OverjoyedVectorPainter,
            text = Res.string.overjoyed,
            rouletteBackgroundColor = CustomColors.Green50,
            rouletteFaceColor = CustomColors.Green70,
            backgroundColor = CustomColors.Green50,
            color = CustomColors.Green80
        )
}

val moods = listOf(
    Mood.Overjoyed,
    Mood.Happy,
    Mood.Neutral,
    Mood.Sad,
    Mood.Depressed,
)