package com.joohnq.shared_resources.remember

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.friday_abrev
import com.joohnq.shared_resources.friday_char
import com.joohnq.shared_resources.monday_abrev
import com.joohnq.shared_resources.monday_char
import com.joohnq.shared_resources.saturday_abrev
import com.joohnq.shared_resources.saturday_char
import com.joohnq.shared_resources.sunday_abrev
import com.joohnq.shared_resources.sunday_char
import com.joohnq.shared_resources.thursday_abrev
import com.joohnq.shared_resources.thursday_char
import com.joohnq.shared_resources.tuesday_abrev
import com.joohnq.shared_resources.tuesday_char
import com.joohnq.shared_resources.wednesday_abrev
import com.joohnq.shared_resources.wednesday_char
import org.jetbrains.compose.resources.StringResource

@Composable fun rememberWeekChars(): List<StringResource> =
    remember {
        listOf(
            Res.string.sunday_char,
            Res.string.monday_char,
            Res.string.tuesday_char,
            Res.string.wednesday_char,
            Res.string.thursday_char,
            Res.string.friday_char,
            Res.string.saturday_char
        )
    }

@Composable fun rememberWeekThreeChars(): List<StringResource> =
    remember {
        listOf(
            Res.string.sunday_abrev,
            Res.string.monday_abrev,
            Res.string.tuesday_abrev,
            Res.string.wednesday_abrev,
            Res.string.thursday_abrev,
            Res.string.friday_abrev,
            Res.string.saturday_abrev
        )
    }
