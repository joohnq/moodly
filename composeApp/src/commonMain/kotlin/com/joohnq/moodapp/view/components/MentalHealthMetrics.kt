package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.MentalMetric
import com.joohnq.moodapp.view.constants.Colors
import org.jetbrains.compose.resources.painterResource

@Composable
fun MentalHealthMetrics(healthMetricsItems: List<MentalMetric>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(healthMetricsItems, key = { it.id }) { metric ->
            Column(
                modifier = Modifier.background(
                    color = metric.backgroundColor,
                    shape = RoundedCornerShape(20.dp)
                ).fillMaxSize().padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(metric.icon),
                        contentDescription = metric.title,
                        tint = Colors.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = metric.title,
                        style = TextStyles.HomeMetricTitle()
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                metric.content(
                    Modifier.heightIn(min = 130.dp, max = 140.dp)
                        .widthIn(min = 130.dp, max = 140.dp).fillMaxSize()
                )
            }
        }
    }
}