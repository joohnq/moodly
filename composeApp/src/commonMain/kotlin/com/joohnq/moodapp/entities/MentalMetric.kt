package com.joohnq.moodapp.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.moodapp.entities.palette.MentalMetricsPalette
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
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
    val palette: MentalMetricsPalette,
    open val content: @Composable (Modifier) -> Unit
) {
    data class FreudScore(override val content: @Composable (Modifier) -> Unit = {}) : MentalMetric(
        id = FREUD_SCORE,
        title = Res.string.freud_score,
        icon = Drawables.Icons.Heart,
        palette = MentalMetricsPalette(
            backgroundColor = Colors.Green50
        ),
        content = content
    )

    data class Mood(override val content: @Composable (Modifier) -> Unit = {}) : MentalMetric(
        id = MOOD,
        title = Res.string.mood,
        icon = Drawables.Icons.SadFace,
        palette = MentalMetricsPalette(
            backgroundColor = Colors.Orange40
        ),
        content = content
    )

    data class HealthJournal(override val content: @Composable (Modifier) -> Unit = {}) :
        MentalMetric(
            id = HEALTH_JOURNAL,
            title = Res.string.health_journal,
            icon = Drawables.Icons.Document,
            palette = MentalMetricsPalette(
                backgroundColor = Colors.Purple30
            ),
            content = content
        )

    companion object {
        private const val FREUD_SCORE = "0"
        private const val MOOD = "1"
        private const val HEALTH_JOURNAL = "2"
    }
}