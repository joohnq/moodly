package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toWordCount
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HealthJournalPanelInfo(
    modifier: Modifier = Modifier,
    progress: Float,
    value: String,
    title: StringResource,
    color: Color,
    icon: DrawableResource
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
                trackColor = Colors.Gray20,
                color = color
            )
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(Res.string.total_words),
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
        VerticalSpacer(8.dp)
        Text(
            text = value,
            style = TextStyles.TextXlBold(),
            color = Colors.Gray80
        )
        VerticalSpacer(2.dp)
        Text(
            text = stringResource(title),
            style = TextStyles.TextSmRegular(),
            color = Colors.Gray60
        )
    }
}

@Composable
fun HealthJournalPanel(
    modifier: Modifier = Modifier,
    count: Int,
    records: List<HealthJournalRecord>
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
            style = TextStyles.Heading2xlExtraBold(),
            color = Colors.White
        )
        VerticalSpacer(10.dp)
        Text(
            text = stringResource(Res.string.total_journals),
            style = TextStyles.TextXlSemiBold(),
            color = Colors.White
        )
        VerticalSpacer(20.dp)
        Row(
            modifier = Modifier
                .background(color = Colors.White, shape = Dimens.Shape.Large)
                .paddingAllSmall()
                .clip(Dimens.Shape.Large)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = 20.dp, alignment = Alignment.CenterHorizontally)
        ) {
            HealthJournalPanelInfo(
                modifier = Modifier.weight(1f),
                progress = 50f,
                value = totalWords.toString(),
                title = Res.string.total_words,
                color = Colors.Brown60,
                icon = Drawables.Icons.Outlined.Eye
            )
            HealthJournalPanelInfo(
                modifier = Modifier.weight(1f),
                progress = 50f,
                value = negative.toString(),
                title = Res.string.negative,
                color = Colors.Pink40,
                icon = Drawables.Icons.Outlined.Negative
            )
            HealthJournalPanelInfo(
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