package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.gratefulness.api.entity.GratefulnessInsight
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.gratefulness_insight_action
import com.joohnq.shared_resources.gratefulness_insight_description
import com.joohnq.shared_resources.gratefulness_insight_not_enough_action
import com.joohnq.shared_resources.gratefulness_insight_not_enough_title
import com.joohnq.shared_resources.gratefulness_insight_title
import com.joohnq.shared_resources.gratitude_insights
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun GratefulnessOverviewInsight(
    modifier: Modifier = Modifier,
    insight: GratefulnessInsight,
    onCreate: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.gratitude_insights
    )
    when {
        insight.items.isEmpty() ->
            NotFoundVerticalLayout(
                modifier = modifier,
                title = Res.string.gratefulness_insight_title,
                subtitle = Res.string.gratefulness_insight_description,
                actionText = Res.string.gratefulness_insight_action,
                image = Drawables.Images.GratefulnessInsight,
                onClick = onCreate
            )

        insight.items.size <= 3 ->
            NotFoundHorizontalLayout(
                modifier = modifier,
                title = Res.string.gratefulness_insight_not_enough_title,
                actionText = Res.string.gratefulness_insight_not_enough_action,
                image = Drawables.Images.GratefulnessInsight,
                onClick = onCreate
            )

        else ->
            GratefulnessOverviewInsightBody(
                modifier = modifier,
                insight = insight
            )
    }
}
