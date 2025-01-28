package com.joohnq.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_journal
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.lets_set_up_daily_journaling_and_self_reflection
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun SelfJournalingNotFound(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
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
            Column(modifier = Modifier.padding(16.dp).weight(1f)) {
                Text(
                    text = stringResource(Res.string.lets_set_up_daily_journaling_and_self_reflection),
                    style = TextStyles.ParagraphSm(),
                    color = Colors.Gray60,
                )
                VerticalSpacer(16.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.add_new_journal),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.Add),
                        contentDescription = null,
                        tint = Colors.Brown60,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Image(
                painter = painterResource(Drawables.Images.SelfJournalingIllustration),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@Composable
fun SelfJournalingMetric(
    resource: HealthJournalRecord?,
    onCreate: () -> Unit,
    onClick: () -> Unit,
) {
    if (resource == null)
        SelfJournalingNotFound(modifier = Modifier.paddingHorizontalMedium(), onClick = onCreate)
    else {
    }

}