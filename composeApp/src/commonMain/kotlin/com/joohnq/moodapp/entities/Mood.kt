package com.joohnq.moodapp.entities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import org.jetbrains.compose.resources.DrawableResource

enum class MoodId{
    Depressed,
    Sad,
    Neutral,
    Happy,
    Overjoyed
}

sealed class Mood(
    val id: MoodId,
    val image: DrawableResource,
    val imageVector: ImageVector,
    val text: String,
    val rouletteBackgroundColor: Color,
    val rouletteFaceColor: Color,
    val backgroundColor: Color,
    val color: Color,
) {
    data object Depressed :
        Mood(
            id = MoodId.Depressed,
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = "Depressed",
            rouletteBackgroundColor = Colors.Purple30,
            rouletteFaceColor = Colors.Purple50,
            backgroundColor = Colors.Purple30,
            color = Colors.Purple80
        )

    data object Sad : Mood(
        id = MoodId.Sad,
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = "Sad",
        rouletteBackgroundColor = Colors.Orange40,
        rouletteFaceColor = Colors.Orange70,
        backgroundColor = Colors.Orange40,
        color = Colors.Orange80
    )

    data object Neutral :
        Mood(
            id = MoodId.Neutral,
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = "Neutral",
            rouletteBackgroundColor = Colors.Brown60,
            rouletteFaceColor = Colors.Brown80,
            backgroundColor = Colors.Brown40,
            color = Colors.Brown80
        )

    data object Happy : Mood(
        id = MoodId.Happy,
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = "Happy",
        rouletteBackgroundColor = Colors.Yellow40,
        rouletteFaceColor = Colors.Yellow70,
        backgroundColor = Colors.Yellow40,
        color = Colors.Yellow80
    )

    data object Overjoyed :
        Mood(
            id = MoodId.Overjoyed,
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = "Overjoyed",
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