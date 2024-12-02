package com.joohnq.moodapp.domain

import androidx.compose.runtime.Composable
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.finances
import moodapp.composeapp.generated.resources.in_peace
import moodapp.composeapp.generated.resources.kids
import moodapp.composeapp.generated.resources.life
import moodapp.composeapp.generated.resources.loneliness
import moodapp.composeapp.generated.resources.other
import moodapp.composeapp.generated.resources.relationship
import moodapp.composeapp.generated.resources.work
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Serializable
sealed class Stressor(
    @Contextual val text: StringResource,
    val id: String
) {
    @Serializable
    data object Work : Stressor(text = Res.string.work, id = WORK)

    @Serializable
    data object Relationship : Stressor(text = Res.string.relationship, id = RELATIONSHIP)

    @Serializable
    data object Kids : Stressor(text = Res.string.kids, id = KIDS)

    @Serializable
    data object Life : Stressor(text = Res.string.life, id = LIFE)

    @Serializable
    data object Finances : Stressor(text = Res.string.finances, id = FINANCES)

    @Serializable
    data object Loneliness : Stressor(text = Res.string.loneliness, id = LONELINESS)

    @Serializable
    data object InPeace : Stressor(text = Res.string.in_peace, id = IN_PEACE)

    @Serializable
    data class Other(val other: String = "") : Stressor(text = Res.string.other, id = other)

    companion object {
        private const val WORK = "0"
        private const val RELATIONSHIP = "1"
        private const val KIDS = "2"
        private const val LIFE = "3"
        private const val FINANCES = "4"
        private const val LONELINESS = "5"
        private const val IN_PEACE = "7"

        fun toValue(src: String): Stressor = when (src) {
            WORK -> Work
            RELATIONSHIP -> Relationship
            KIDS -> Kids
            LIFE -> Life
            LONELINESS -> Loneliness
            FINANCES -> Finances
            IN_PEACE -> InPeace
            else -> Other(src)
        }

        fun fromValue(stressor: Stressor?): String = stressor?.id ?: ""

        fun getAll(): List<Stressor> =
            listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other())

        @Composable fun getText(stressors: List<Stressor>): String =
            stressors.map { stressor ->
                if (stressor is Other) {
                    stressor.other.replaceFirstChar { it.uppercase() }
                } else {
                    stringResource(stressor.text)
                }
            }.joinToString(separator = ", ")
    }
}