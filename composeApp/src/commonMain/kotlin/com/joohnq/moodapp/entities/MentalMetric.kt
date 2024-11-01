package com.joohnq.moodapp.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.moodapp.entities.palette.MentalMetricsPalette
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.health_journal
import moodapp.composeapp.generated.resources.mood
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class MentalMetric(
    val id: String,
    val title: StringResource,
    val icon: DrawableResource,
    open val content: @Composable (Modifier) -> Unit
) {
    data class FreudScore(override val content: @Composable (Modifier) -> Unit = {}) : MentalMetric(
        id = FREUD_SCORE,
        title = Res.string.freud_score,
        icon = Drawables.Icons.Heart,
        content = content
    )

    data class Mood(override val content: @Composable (Modifier) -> Unit = {}) : MentalMetric(
        id = MOOD,
        title = Res.string.mood,
        icon = Drawables.Icons.SadFace,
        content = content
    )

    data class HealthJournal(override val content: @Composable (Modifier) -> Unit = {}) :
        MentalMetric(
            id = HEALTH_JOURNAL,
            title = Res.string.health_journal,
            icon = Drawables.Icons.Document,
            content = content
        )

    companion object {
        private const val FREUD_SCORE = "0"
        private const val MOOD = "1"
        private const val HEALTH_JOURNAL = "2"

        fun getPalette(value: MentalMetric): MentalMetricsPalette = when (value) {
            is FreudScore -> MentalMetricsPalette(
                backgroundColor = Colors.Green50
            )

            is Mood -> MentalMetricsPalette(
                backgroundColor = Colors.Orange40
            )

            is HealthJournal -> MentalMetricsPalette(
                backgroundColor = Colors.Purple30
            )
        }
    }
}