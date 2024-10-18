package com.joohnq.moodapp.model.entities

import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.five_number
import moodapp.composeapp.generated.resources.four_number
import moodapp.composeapp.generated.resources.one_number
import moodapp.composeapp.generated.resources.three_number
import moodapp.composeapp.generated.resources.two_number
import moodapp.composeapp.generated.resources.you_are_a_little_stressed_out
import moodapp.composeapp.generated.resources.you_are_extremely_stressed_out
import moodapp.composeapp.generated.resources.you_are_neutral
import moodapp.composeapp.generated.resources.you_are_not_stressed_out
import moodapp.composeapp.generated.resources.you_are_very_stressed_out
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class StressLevel(
    val id: String,
    @Contextual val value: StringResource,
    @Contextual val text: StringResource
) {
    @Serializable
    data object One :
        StressLevel(
            id = ONE,
            value = Res.string.one_number,
            text = Res.string.you_are_not_stressed_out
        )

    @Serializable
    data object Two :
        StressLevel(
            id = TWO,
            value = Res.string.two_number,
            text = Res.string.you_are_a_little_stressed_out
        )

    @Serializable
    data object Three :
        StressLevel(
            id = THREE,
            value = Res.string.three_number,
            text = Res.string.you_are_neutral
        )

    @Serializable
    data object Four :
        StressLevel(
            id = FOUR,
            value = Res.string.four_number,
            text = Res.string.you_are_very_stressed_out
        )

    @Serializable
    data object Five :
        StressLevel(
            id = FIVE,
            value = Res.string.five_number,
            text = Res.string.you_are_extremely_stressed_out
        )

    companion object {
        const val ONE = "1"
        const val TWO = "2"
        const val THREE = "3"
        const val FOUR = "4"
        const val FIVE = "5"

        fun toValue(src: String): StressLevel = when (src) {
            ONE -> One
            TWO -> Two
            THREE -> Three
            FOUR -> Four
            FIVE -> Five
            else -> throw IllegalArgumentException("Unknown stress rate option: $src")
        }

        fun fromValue(stressLevel: StressLevel?): String = stressLevel?.id.toString()

        fun getAll(): List<StressLevel> = listOf(
            One,
            Two,
            Three,
            Four,
            Five
        )

        fun getSaver(): Saver<StressLevel, String> = Saver(
            save = { fromValue(it) },
            restore = { toValue(it) }
        )
    }
}
