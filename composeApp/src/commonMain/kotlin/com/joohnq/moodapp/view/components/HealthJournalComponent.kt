package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.mappers.items
import com.joohnq.moodapp.view.constants.Colors

@Composable
fun HealthJournalComponent(
    modifier: Modifier = Modifier,
    healthJournal: Map<String, List<StatsRecord>?>
) {
    val dayOfWeek = DatetimeHelper.weekDayByMonth(DatetimeHelper.getLocalDateTime())
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(dayOfWeek + 1) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = Colors.Transparent, shape = RoundedCornerShape(4.dp))
                    .size(18.dp),
            )
        }
        items(healthJournal) { statsRecords ->
            val background = when {
                statsRecords?.size == 1 -> Colors.Purple10
                statsRecords?.size != null && statsRecords.size >= 2 -> Colors.White
                else -> Colors.Purple20
            }
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = background, shape = RoundedCornerShape(4.dp))
                    .size(18.dp),
            )
        }
    }
}


@Composable
fun HealthJournalComponentColorful(
    modifier: Modifier = Modifier,
    healthJournal: Map<String, List<StatsRecord>?>
) {
    val dayOfWeek = DatetimeHelper.weekDayByMonth(DatetimeHelper.getLocalDateTime())
    BoxWithConstraints {
        val boxSize = (maxWidth - 88.dp) / 7
        LazyVerticalGrid(
            modifier = modifier.heightIn(min = boxSize * 4 + 24.dp, max = boxSize * 5 + 32.dp)
                .padding(horizontal = 20.dp),
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dayOfWeek + 1) {
                Box(
                    modifier = Modifier
                        .background(color = Colors.Transparent, shape = CircleShape)
                        .size(boxSize),
                )
            }
            items(healthJournal) { statsRecords ->
                val background = if (statsRecords == null) Colors.White else {
                    val freudScore = MoodsManager.getFreudScore(statsRecords)
                    freudScore.palette.color
                }
                val border = Modifier.border(
                    width = 1.dp,
                    color = if (statsRecords == null) Colors.Brown30 else background,
                    shape = CircleShape
                )
                Box(
                    modifier = Modifier
                        .background(color = background, shape = CircleShape).size(boxSize)
                        .then(border),
                )
            }
        }
    }
}