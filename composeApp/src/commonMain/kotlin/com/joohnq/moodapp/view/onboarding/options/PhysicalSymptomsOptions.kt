package com.joohnq.moodapp.view.onboarding.options

import androidx.compose.runtime.saveable.Saver
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.view.entities.IconProps
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.no_physical_pain
import moodapp.composeapp.generated.resources.yes_but_just_a_bit
import moodapp.composeapp.generated.resources.yes_very_painful
import org.jetbrains.compose.resources.StringResource

sealed class PhysicalSymptomsOptions(
    val id: String,
    val text: StringResource,
    val icon: IconProps
) {
    data object YesVeryPainful : PhysicalSymptomsOptions(
        id = YesVeryPainfulId,
        text = Res.string.yes_very_painful, icon = IconProps(
            icon = Drawables.Icons.Check,
        )
    )

    data object No : PhysicalSymptomsOptions(
        id = NoId,
        text = Res.string.no_physical_pain,
        icon = IconProps(icon = Drawables.Icons.Close)
    )

    data object YesJustABit : PhysicalSymptomsOptions(
        id = YesJustABitId,
        text = Res.string.yes_but_just_a_bit,
        icon = IconProps(icon = Drawables.Icons.Question)
    )

    data object Indeterminate :
        PhysicalSymptomsOptions(
            id = IndeterminateId,
            text = Res.string.indeterminate,
            icon = IconProps(icon = Drawables.Icons.Question)
        )

    companion object {
        const val YesVeryPainfulId = "0"
        const val NoId = "1"
        const val YesJustABitId = "2"
        const val IndeterminateId = "-1"

        fun valueOf(src: String): PhysicalSymptomsOptions = when (src) {
            YesVeryPainfulId -> YesVeryPainful
            NoId -> No
            YesJustABitId -> YesJustABit
            IndeterminateId -> Indeterminate
            else -> throw IllegalArgumentException("Unknown physical symptoms: $src")
        }
    }
}

val PhysicalSymptomsOptionsSaver = Saver<PhysicalSymptomsOptions, String>(
    save = { opt -> opt.id },
    restore = { name -> PhysicalSymptomsOptions.valueOf(name) }
)