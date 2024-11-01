package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.view.constants.Colors

@Composable
fun HealthJournalComponent(modifier: Modifier = Modifier, mouthMood: List<StatsRecord?>) {
    val dayOfWeek = DatetimeHelper.weekDayByMonth(DatetimeHelper.getLocalDate())
    LazyVerticalGrid(
        modifier = modifier.wrapContentSize(),
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(dayOfWeek + 1) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = Colors.Purple30, shape = RoundedCornerShape(4.dp))
                    .size(18.dp),
            )
        }
        itemsIndexed(mouthMood) { i: Int, statsRecord: StatsRecord? ->
            val background = if (statsRecord == null) Colors.Purple20 else Colors.Purple10
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = background, shape = RoundedCornerShape(4.dp)).size(18.dp),
            )
        }
    }
}