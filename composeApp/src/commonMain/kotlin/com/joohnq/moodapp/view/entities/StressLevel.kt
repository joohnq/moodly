package com.joohnq.moodapp.view.entities

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
            id = OneId,
            value = Res.string.one_number,
            text = Res.string.you_are_not_stressed_out
        )

    @Serializable
    data object Two :
        StressLevel(
            id = TwoId,
            value = Res.string.two_number,
            text = Res.string.you_are_a_little_stressed_out
        )

    @Serializable
    data object Three :
        StressLevel(
            id = ThreeId,
            value = Res.string.three_number,
            text = Res.string.you_are_neutral
        )

    @Serializable
    data object Four :
        StressLevel(
            id = FourId,
            value = Res.string.four_number,
            text = Res.string.you_are_very_stressed_out
        )

    @Serializable
    data object Five :
        StressLevel(
            id = FiveId,
            value = Res.string.five_number,
            text = Res.string.you_are_extremely_stressed_out
        )

    companion object {
        const val OneId = "0"
        const val TwoId = "1"
        const val ThreeId = "2"
        const val FourId = "3"
        const val FiveId = "4"

        fun valueOf(src: String): StressLevel = when (src) {
            OneId -> One
            TwoId -> Two
            ThreeId -> Three
            FourId -> Four
            FiveId -> Five
            else -> throw IllegalArgumentException("Unknown stress rate option: $src")
        }

        fun getAll() = listOf(
            One,
            Two,
            Three,
            Four,
            Five
        )
    }
}

val StressLevelSaver = Saver<StressLevel, String>(
    save = { opt -> opt.id },
    restore = { name -> StressLevel.valueOf(name) }
)
