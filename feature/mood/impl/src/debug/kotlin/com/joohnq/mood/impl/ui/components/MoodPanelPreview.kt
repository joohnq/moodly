package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.toFormattedTimeString
import com.joohnq.mood.impl.ui.fake.depressedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.happyMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.neutralMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.overjoyedMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.fake.sadMoodRecordResourcePreview
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MoodPanelDepressedPreview() {
    MoodPanel(
        record = depressedMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun MoodPanelSadPreview() {
    MoodPanel(
        record = sadMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun MoodPanelNeutralPreview() {
    MoodPanel(
        record = neutralMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun MoodPanelHappyPreview() {
    MoodPanel(
        record = happyMoodRecordResourcePreview
    )
}

@Preview
@Composable
fun MoodPanelOverjoyedPreview() {
    MoodPanel(
        record = overjoyedMoodRecordResourcePreview
    )
}