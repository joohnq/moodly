package com.joohnq.self_journal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.StringMapper.toWordCount
import com.joohnq.mood.api.entity.Mood
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.negative
import com.joohnq.shared_resources.positive
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.total_journals
import com.joohnq.shared_resources.total_words
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalOverviewPanel(
    modifier: Modifier = Modifier,
    count: Int,
    records: List<SelfJournalRecordResource>,
) {
    val totalWords = records.sumOf { it.description.toWordCount() }
    val positive = records.filter { it.mood == Mood.Happy || it.mood == Mood.Overjoyed }.size
    val negative = records.filter { it.mood == Mood.Depressed || it.mood == Mood.Sad }.size

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Outlined.BookOpen),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = Colors.White
        )
        Text(
            text = count.toString(),
            style = TextStyles.heading2xlExtraBold(),
            color = Colors.White
        )
        VerticalSpacer(10.dp)
        Text(
            text = stringResource(Res.string.total_journals),
            style = TextStyles.textXlSemiBold(),
            color = Colors.White
        )
        VerticalSpacer(20.dp)
        Row(
            modifier =
                Modifier
                    .background(color = Colors.White, shape = Dimens.Shape.Large)
                    .paddingAllSmall()
                    .clip(Dimens.Shape.Large)
                    .fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(
                    space = 20.dp,
                    alignment = Alignment.CenterHorizontally
                )
        ) {
            SelfJournalOverviewPanelInfo(
                modifier = Modifier.weight(1f),
                progress = 50f,
                value = totalWords.toString(),
                title = Res.string.total_words,
                color = Colors.Brown60,
                icon = Drawables.Icons.Outlined.Eye
            )
            SelfJournalOverviewPanelInfo(
                modifier = Modifier.weight(1f),
                progress = 50f,
                value = negative.toString(),
                title = Res.string.negative,
                color = Colors.Pink40,
                icon = Drawables.Icons.Outlined.Negative
            )
            SelfJournalOverviewPanelInfo(
                modifier = Modifier.weight(1f),
                progress = 50f,
                value = positive.toString(),
                title = Res.string.positive,
                color = Colors.Green40,
                icon = Drawables.Icons.Outlined.Positive
            )
        }
    }
}
