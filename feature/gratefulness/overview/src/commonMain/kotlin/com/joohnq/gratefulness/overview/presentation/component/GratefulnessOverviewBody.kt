package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.entity.GratefulnessInsight
import com.joohnq.gratefulness.api.entity.Quote

@Composable
fun GratefulnessOverviewBody(
    modifier: Modifier = Modifier,
    items: List<Gratefulness>,
    quote: Quote?,
    insight: GratefulnessInsight,
    onDelete: (Long) -> Unit = {},
    onCreate: () -> Unit = {},
    onSeeMore: () -> Unit = {},
) {
    GratefulnessOverviewHistory(
        modifier = modifier,
        items = items.take(7),
        onDelete = onDelete,
        onCreate = onCreate,
        onSeeMore = onSeeMore
    )
    quote?.let {
        GratefulnessOverviewQuote(
            modifier = modifier,
            quote = it
        )
    }
    GratefulnessOverviewInsight(
        modifier = modifier,
        insight = insight,
        onCreate = onCreate
    )
}
