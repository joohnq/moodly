package com.joohnq.moodapp.view.constants

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.urbanist_black
import moodapp.composeapp.generated.resources.urbanist_bold
import moodapp.composeapp.generated.resources.urbanist_extra_bold
import moodapp.composeapp.generated.resources.urbanist_extra_light
import moodapp.composeapp.generated.resources.urbanist_light
import moodapp.composeapp.generated.resources.urbanist_medium
import moodapp.composeapp.generated.resources.urbanist_regular
import moodapp.composeapp.generated.resources.urbanist_semi_bold
import moodapp.composeapp.generated.resources.urbanist_thin
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