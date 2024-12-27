package com.joohnq.domain.entity

import com.joohnq.mood.theme.Drawables
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.no_physical_pain
import com.joohnq.shared.ui.yes_but_just_a_bit
import com.joohnq.shared.ui.yes_very_painful
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class PhysicalSymptoms(
    val id: Int,
    @Contextual val text: StringResource,
    @Contextual val icon: Icon
) {

    @Serializable
    data object YesVeryPainful : PhysicalSymptoms(
        id = YES_VERY_PAINFUL,
        text = Res.string.yes_very_painful,
        icon = Icon(
            icon = Drawables.Icons.Check,
            contentDescription = Res.string.yes_very_painful
        )
    )

    @Serializable
    data object No : PhysicalSymptoms(
        id = NO,
        text = Res.string.no_physical_pain,
        icon = Icon(
            icon = Drawables.Icons.Close,
            contentDescription = Res.string.no_physical_pain
        )
    )

    @Serializable
    data object YesJustABit : PhysicalSymptoms(
        id = YES_JUST_A_BIT,
        text = Res.string.yes_but_just_a_bit,
        icon = Icon(
            icon = Drawables.Icons.Question,
            contentDescription = Res.string.yes_but_just_a_bit
        )
    )

    companion object {
        private const val YES_VERY_PAINFUL = 0
        private const val NO = 1
        private const val YES_JUST_A_BIT = 2

        fun toValue(src: Int): PhysicalSymptoms = when (src) {
            YES_VERY_PAINFUL -> YesVeryPainful
            NO -> No
            YES_JUST_A_BIT -> YesJustABit
            else -> throw IllegalArgumentException("Unknown physical symptoms: $src")
        }

        fun fromValue(physicalSymptoms: PhysicalSymptoms?): Int = physicalSymptoms?.id ?: -1

        fun getAll(): List<PhysicalSymptoms> = listOf(YesVeryPainful, No, YesJustABit)
    }
}
