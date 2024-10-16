package com.joohnq.moodapp.view.entities

import androidx.compose.runtime.saveable.Saver
import com.joohnq.moodapp.Drawables
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.no_physical_pain
import moodapp.composeapp.generated.resources.yes_but_just_a_bit
import moodapp.composeapp.generated.resources.yes_very_painful
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class PhysicalSymptoms(
    val id: String,
    @Contextual val text: StringResource,
    @Contextual val icon: IconProps
) {
    @Serializable
    data object YesVeryPainful : PhysicalSymptoms(
        id = YesVeryPainfulId,
        text = Res.string.yes_very_painful, icon = IconProps(
            icon = Drawables.Icons.Check,
        )
    )

    @Serializable
    data object No : PhysicalSymptoms(
        id = NoId,
        text = Res.string.no_physical_pain,
        icon = IconProps(icon = Drawables.Icons.Close)
    )

    @Serializable
    data object YesJustABit : PhysicalSymptoms(
        id = YesJustABitId,
        text = Res.string.yes_but_just_a_bit,
        icon = IconProps(icon = Drawables.Icons.Question)
    )

    companion object {
        const val YesVeryPainfulId = "0"
        const val NoId = "1"
        const val YesJustABitId = "2"

        fun valueOf(src: String): PhysicalSymptoms = when (src) {
            YesVeryPainfulId -> YesVeryPainful
            NoId -> No
            YesJustABitId -> YesJustABit
            else -> throw IllegalArgumentException("Unknown physical symptoms: $src")
        }

        fun getAll(): List<PhysicalSymptoms> = listOf(YesVeryPainful, No, YesJustABit)
    }
}

val PhysicalSymptomsSaver = Saver<PhysicalSymptoms?, String>(
    save = { opt -> opt?.id },
    restore = { name -> PhysicalSymptoms.valueOf(name) }
)