package com.joohnq.self_journal.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.mapper.MoodResourceMapper.allMoodResource
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.journal_insight
import com.joohnq.shared_resources.lets_log_your_first_journal_to_see_your_insight
import com.joohnq.shared_resources.log_your_first_journal
import com.joohnq.shared_resources.most_frequent_emotion
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.write_journal
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalOverviewInsight(
    modifier: Modifier = Modifier,
    items: List<Pair<MoodResource, Int>>,
    onCreate: () -> Unit = {},
) {
    val moodResources = remember { allMoodResource() }

    SectionHeader(
        modifier = modifier,
        title = Res.string.journal_insight
    )

    if (items.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            image = Drawables.Images.SelfJournalInsight,
            title = Res.string.log_your_first_journal,
            description = Res.string.lets_log_your_first_journal_to_see_your_insight,
            text = Res.string.write_journal,
            icon = Drawables.Icons.Outlined.Edit,
            onCreate = onCreate
        )
    } else {
        Card(
            modifier = modifier,
            shape = Dimens.Shape.Large,
            colors =
                CardColors(
                    containerColor = Colors.Gray5,
                    contentColor = Color.Unspecified,
                    disabledContainerColor = Colors.Gray5,
                    disabledContentColor = Color.Unspecified
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().paddingAllSmall()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = stringResource(items.first().first.text),
                            style = TextStyles.headingXsBold(),
                            color = Colors.Gray80
                        )
                        Text(
                            text = stringResource(Res.string.most_frequent_emotion),
                            style = TextStyles.textSmRegular(),
                            color = Colors.Gray60
                        )
                    }
                }
                VerticalSpacer(16.dp)
                HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Colors.Gray20)
                VerticalSpacer(16.dp)
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    moodResources.reversed().forEach { mood ->
                        val count = items.find { it.first == mood }?.second ?: 0

                        SelfJournalInsightItem(
                            count = count,
                            percentage = 100f * count.toFloat() / items.size,
                            color = mood.palette.color,
                            face = mood.assets.secondaryIcon
                        )
                    }
                }
            }
        }
    }
}
