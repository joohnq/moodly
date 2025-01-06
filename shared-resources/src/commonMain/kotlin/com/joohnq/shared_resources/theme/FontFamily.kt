package com.joohnq.shared_resources.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.urbanist_black
import com.joohnq.shared_resources.urbanist_bold
import com.joohnq.shared_resources.urbanist_extra_bold
import com.joohnq.shared_resources.urbanist_extra_light
import com.joohnq.shared_resources.urbanist_light
import com.joohnq.shared_resources.urbanist_medium
import com.joohnq.shared_resources.urbanist_regular
import com.joohnq.shared_resources.urbanist_semi_bold
import com.joohnq.shared_resources.urbanist_thin
import org.jetbrains.compose.resources.Font

object FontFamily {
    object Urbanist {
        @Composable
        fun Black() =
            FontFamily(
                Font(Res.font.urbanist_black, FontWeight.Black, FontStyle.Normal)
            )

        @Composable
        fun ExtraBold() =
            FontFamily(Font(Res.font.urbanist_extra_bold, FontWeight.ExtraBold, FontStyle.Normal))

        @Composable
        fun Bold() = FontFamily(Font(Res.font.urbanist_bold, FontWeight.Bold, FontStyle.Normal))

        @Composable
        fun SemiBold() =
            FontFamily(Font(Res.font.urbanist_semi_bold, FontWeight.SemiBold, FontStyle.Normal))

        @Composable
        fun Medium() =
            FontFamily(Font(Res.font.urbanist_medium, FontWeight.Medium, FontStyle.Normal))

        @Composable
        fun Regular() =
            FontFamily(Font(Res.font.urbanist_regular, FontWeight.Normal, FontStyle.Normal))

        @Composable
        fun Light() = FontFamily(Font(Res.font.urbanist_light, FontWeight.Light, FontStyle.Normal))

        @Composable
        fun ExtraLight() =
            FontFamily(Font(Res.font.urbanist_extra_light, FontWeight.ExtraLight, FontStyle.Normal))

        @Composable
        fun Thin() = FontFamily(Font(Res.font.urbanist_thin, FontWeight.Thin, FontStyle.Normal))
    }
}