package com.joohnq.moodapp.model.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import org.jetbrains.compose.resources.DrawableResource

sealed class MentalMetric(
    val id: String,
    val title: String,
    val icon: DrawableResource,
    val backgroundColor: Color,
    open val content: @Composable (Modifier) -> Unit
) {
    data class FreudScore(override val content: @Composable (Modifier) -> Unit = {}) : MentalMetric(
        id = FREUD_SCORE,
        title = "Freud Score",
        icon = Drawables.Icons.Heart,
        backgroundColor = Colors.Green50,
        content = content
    )

    data class Mood(override val content: @Composable (Modifier) -> Unit = {}) : MentalMetric(
        id = MOOD,
        title = "Mood",
        icon = Drawables.Icons.SadFace,
        backgroundColor = Colors.Orange40,
        content = content
    )

    data class HealthJournal(override val content: @Composable (Modifier) -> Unit = {}) :
        MentalMetric(
            id = HEALTH_JOURNAL,
            title = "Health Journal",
            icon = Drawables.Icons.Document,
            backgroundColor = Colors.Purple30,
            content = content
        )

    companion object {
        private const val FREUD_SCORE = "0"
        private const val MOOD = "1"
        private const val HEALTH_JOURNAL = "2"
    }
}