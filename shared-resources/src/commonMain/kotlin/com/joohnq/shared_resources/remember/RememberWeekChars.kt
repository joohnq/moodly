package com.joohnq.shared_resources.remember

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.friday_char
import com.joohnq.shared_resources.monday_char
import com.joohnq.shared_resources.saturday_char
import com.joohnq.shared_resources.sunday_char
import com.joohnq.shared_resources.thursday_char
import com.joohnq.shared_resources.tuesday_char
import com.joohnq.shared_resources.wednesday_char
import org.jetbrains.compose.resources.StringResource

@Composable fun rememberWeekChars(): List<StringResource> = remember {
    listOf(
        Res.string.sunday_char,
        Res.string.monday_char,
        Res.string.tuesday_char,
        Res.string.wednesday_char,
        Res.string.thursday_char,
        Res.string.friday_char,
        Res.string.saturday_char,
    )
}
