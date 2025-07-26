package com.joohnq.self_journal.impl.ui.components

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
import com.joohnq.mood.impl.ui.mapper.getAllMoodResource
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun JournalInsight(
    modifier: Modifier = Modifier,
    records: List<SelfJournalRecordResource>
) {
    val groupedMoods =
        records
            .groupBy { it.mood }
            .map { it.key to it.value.size }
            .sortedBy { it.first.id }

    val moodResources = remember { getAllMoodResource() }

    if (groupedMoods.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            image = Drawables.Images.SelfJournalInsight,
            title = Res.string.log_your_first_journal,
            description = Res.string.lets_log_your_first_journal_to_see_your_insight,
            text = Res.string.write_journal,
            icon = Drawables.Icons.Outlined.Edit,
            onCreate = {}
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
                            text = stringResource(groupedMoods.first().first.text),
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
                        val count = groupedMoods.find { it.first == mood }?.second ?: 0

                        JournalInsightItem(
                            count = count,
                            percentage = 100f * count.toFloat() / records.size,
                            color = mood.palette.color,
                            face = mood.assets.secondaryIcon
                        )
                    }
                }
            }
        }
    }
}
