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
            backgroundColor = Colors.Purple20,
            color = Colors.Purple80
        )

    data object Sad : Mood(
        image = Drawables.Mood.Sad,
        imageVector = Drawables.Mood.SadVectorPainter,
        text = "Sad",
        backgroundColor = Colors.Orange30,
        color = Colors.Orange80
    )

    data object Neutral :
        Mood(
            image = Drawables.Mood.Neutral,
            imageVector = Drawables.Mood.NeutralVectorPainter,
            text = "Neutral",
            backgroundColor = Colors.Brown40,
            color = Colors.Brown80
        )

    data object Happy : Mood(
        image = Drawables.Mood.Happy,
        imageVector = Drawables.Mood.HappyVectorPainter,
        text = "Happy",
        backgroundColor = Colors.Yellow20,
        color = Colors.Yellow80
    )

    data object Overjoyed :
        Mood(
            image = Drawables.Mood.Overjoyed,
            imageVector = Drawables.Mood.OverjoyedVectorPainter,
            text = "Overjoyed",
            backgroundColor = Colors.Green30,
            color = Colors.Green80
        )
}