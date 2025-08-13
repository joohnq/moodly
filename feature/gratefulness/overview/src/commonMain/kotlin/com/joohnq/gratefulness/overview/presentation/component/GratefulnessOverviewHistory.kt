package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.impl.ui.presentation.component.GratefulnessHistoryCard
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_gratitute
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.gratitude_history
import com.joohnq.shared_resources.lets_set_up_daily_gratitude_and_affirmations
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun GratefulnessOverviewHistory(
    modifier: Modifier = Modifier,
    items: List<Gratefulness>,
    onCreate: () -> Unit,
    onDelete: (Int) -> Unit,
    onSeeMore: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.gratitude_history,
        onSeeMore = onSeeMore
    )
    if (items.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.lets_set_up_daily_gratitude_and_affirmations,
            actionText = Res.string.add_new_gratitute,
            image = Drawables.Images.GratefulnessSelfLove,
            onClick = onCreate
        )
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { gratefulness ->
                GratefulnessHistoryCard(
                    modifier = modifier,
                    gratefulness = gratefulness,
                    onDelete = onDelete
                )
            }
        }
    }
}
