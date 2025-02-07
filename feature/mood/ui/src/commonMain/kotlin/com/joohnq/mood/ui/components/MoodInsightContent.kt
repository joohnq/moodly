package com.joohnq.mood.ui.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.mapper.getStreakDays
import com.joohnq.mood.domain.mapper.getWeekRecords
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.SectionHeader
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
    containerColor: Color = Colors.White,
    records: List<MoodRecord>,
    onClick: () -> Unit
) {
    val streakDays = records.getWeekRecords().getStreakDays()
    SectionHeader(
        modifier = modifier,
        title = Res.string.mood_insight
    )
    Card(
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Colors.Brown80,
            disabledContainerColor = containerColor,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
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
                    style = TextStyles.HeadingXsBold(),
                    color = Colors.Gray80,
                )
                Text(
                    text = stringResource(Res.string.mood_streak),
                    style = TextStyles.TextMdMedium(),
                    color = Colors.Gray60,
                )
                Text(
                    text = stringResource(Res.string.you_ve_checked_in_your_mood_for_days_straight, streakDays),
                    style = TextStyles.ParagraphSm(),
                    color = Colors.Gray60,
                )
            }
            Image(
                painter = painterResource(Drawables.Images.MoodInsightStrike),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }
    }
}