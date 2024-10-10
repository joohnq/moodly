package com.joohnq.moodapp.entities

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.icons.MoodDepressed
import com.joohnq.moodapp.icons.MoodHappy
import com.joohnq.moodapp.icons.MoodNeutral
import com.joohnq.moodapp.icons.MoodOverjoyed
import com.joohnq.moodapp.icons.MoodSad
import org.jetbrains.compose.resources.DrawableResource

sealed class Mood(
    val image: DrawableResource,
    val imageVector: ImageVector,
    val text: String,
    val backgroundColor: Color,
    val color: Color
) {
    data object Depressed :
        Mood(
            image = Drawables.Mood.Depressed,
            imageVector = Drawables.Mood.DepressedVectorPainter,
            text = "Depressed",
            backgroundColor = Colors.Purple30,
            color = Colors.Purple50
        )

    data object Sad : Mood(
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = "Sad",
        backgroundColor = Colors.Orange40,
        color = Colors.Orange70
    )

    data object Neutral :
        Mood(
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = "Neutral",
            backgroundColor = Colors.Brown60,
            color = Colors.Brown80
        )

    data object Happy : Mood(
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = "Happy",
        backgroundColor = Colors.Yellow40,
        color = Colors.Yellow70
    )

    data object Overjoyed :
        Mood(
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = "Overjoyed",
            backgroundColor = Colors.Green50,
            color = Colors.Green70
        )
}