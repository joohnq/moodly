package com.joohnq.moodapp.entities

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
sealed class Stressors(
    @Contextual val text: StringResource,
    val id: String
) {
    @Serializable
    data object Work : Stressors(text = Res.string.work, id = WORK)

    @Serializable
    data object Relationship : Stressors(text = Res.string.relationship, id = RELATIONSHIP)

    @Serializable
    data object Kids : Stressors(text = Res.string.kids, id = KIDS)

    @Serializable
    data object Life : Stressors(text = Res.string.life, id = LIFE)

    @Serializable
    data object Finances : Stressors(text = Res.string.finances, id = FINANCES)

    @Serializable
    data object Loneliness : Stressors(text = Res.string.loneliness, id = LONELINESS)

    @Serializable
    data object InPeace : Stressors(text = Res.string.in_peace, id = IN_PEACE)

    @Serializable
    data class Other(val other: String = "") : Stressors(text = Res.string.other, id = other)

    companion object {
        private const val WORK = "0"
        private const val RELATIONSHIP = "1"
        private const val KIDS = "2"
        private const val LIFE = "3"
        private const val FINANCES = "4"
        private const val LONELINESS = "5"
        private const val IN_PEACE = "7"

        fun toValue(src: String): Stressors = when (src) {
            WORK -> Work
            RELATIONSHIP -> Relationship
            KIDS -> Kids
            LIFE -> Life
            LONELINESS -> Loneliness
            FINANCES -> Finances
            IN_PEACE -> InPeace
            else -> Other(src)
        }

        fun fromValue(stressors: Stressors?): String = stressors?.id ?: ""

        fun getAll(): List<Stressors> =
            listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other())

        @Composable fun getText(stressors: List<Stressors>): String =
            stressors.map { stressor ->
                if (stressor is Other) {
                    stressor.id.replaceFirstChar { it.uppercase() }
                } else {
                    stringResource(stressor.text)
                }
            }.joinToString(separator = ", ")
    }
}