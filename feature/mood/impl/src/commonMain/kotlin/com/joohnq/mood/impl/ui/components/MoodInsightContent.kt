package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.mapper.getStreakDays
import com.joohnq.mood.impl.ui.mapper.getWeekRecords
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodInsightContent(
    modifier: Modifier = Modifier,
    records: List<MoodRecordResource>,
) {
    val weekRecords = records.getWeekRecords()
    val streakDays = weekRecords.getStreakDays()

    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_insight
    )
    Card(
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Colors.Brown80,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Colors.Brown80
            ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.paddingAllSmall().weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(Res.string.day_streak, streakDays),
                    style = TextStyles.headingXsBold(),
                    color = Colors.Gray80
                )
                VerticalSpacer(3.dp)
                Text(
                    text = stringResource(Res.string.mood_streak),
                    style = TextStyles.textMdMedium(),
                    color = Colors.Gray60
                )
                VerticalSpacer(3.dp)
                Text(
                    text = stringResource(Res.string.you_ve_checked_in_your_mood_for_days_straight, streakDays),
                    style = TextStyles.paragraphSm(),
                    color = Colors.Gray60
                )
            }
            Image(
                painter = painterResource(Drawables.Images.MoodInsightStrike),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}
