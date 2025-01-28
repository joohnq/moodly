import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.calculateDuration
import com.joohnq.core.ui.mapper.toHoursAndMinutesString
import com.joohnq.core.ui.mapper.toMonthAbbreviatedAndDayString
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityHistoryCard(
    modifier: Modifier = Modifier,
    record: SleepQualityRecord,
    onClick: () -> Unit,
) {
    val duration = Pair(record.startSleeping, record.endSleeping).calculateDuration()

    Card(
        modifier = modifier.fillMaxWidth().paddingHorizontalMedium(),
        colors = CardColors(
            containerColor = Colors.Gray10,
            contentColor = Colors.Gray80,
            disabledContainerColor = Colors.Gray10,
            disabledContentColor = Colors.Gray80
        ),
        shape = Dimens.Shape.Medium,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Sleep),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Colors.Brown60
            )
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = duration.toHoursAndMinutesString(),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown80
                )
                if (record.sleepInfluences.isNotEmpty())
                    Text(
                        text = record.sleepInfluences.joinToString(", "),
                        style = TextStyles.TextMdBold(),
                        color = Colors.Brown80,
                        overflow = TextOverflow.Ellipsis
                    )
            }
            HorizontalSpacer(20.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = record.createdAt.toMonthAbbreviatedAndDayString(),
                    style = TextStyles.TextSmRegular(),
                    color = Colors.Gray60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.ArrowOpen),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).rotate(180f),
                    tint = Colors.Gray60
                )
            }
        }
    }
}

@Preview
@Composable
fun SleepQualityHistoryCardPreview() {
    SleepQualityHistoryCard(
        record = SleepQualityRecord(),
        onClick = {}
    )
}