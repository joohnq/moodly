package com.joohnq.moodapp.entities

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

@Serializable
sealed class Stressors(
    @Contextual val text: StringResource,
    val value: String
) {
    @Serializable
    data object Work : Stressors(text = Res.string.work, value = WORK)

    @Serializable
    data object Relationship : Stressors(text = Res.string.relationship, value = RELATIONSHIP)

    @Serializable
    data object Kids : Stressors(text = Res.string.kids, value = KIDS)

    @Serializable
    data object Life : Stressors(text = Res.string.life, value = LIFE)

    @Serializable
    data object Finances : Stressors(text = Res.string.finances, value = FINANCES)

    @Serializable
    data object Loneliness : Stressors(text = Res.string.loneliness, value = LONELINESS)

    @Serializable
    data object InPeace : Stressors(text = Res.string.in_peace, value = IN_PEACE)

    @Serializable
    data class Other(val other: String = "") : Stressors(text = Res.string.other, value = other)

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

        fun fromValue(stressors: Stressors?): String = stressors?.value ?: ""

        fun getAll(): List<Stressors> =
            listOf(Work, Relationship, Kids, Life, Finances, Loneliness, Other())
    }
}