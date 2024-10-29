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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.view.constants.Colors

@Composable
fun HealthJournalComponent(modifier: Modifier = Modifier, mouthMood: List<StatsRecord?>) {
    if (mouthMood.size > 31 || mouthMood.size < 28) return
    LazyVerticalGrid(
        modifier = modifier.wrapContentSize(),
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        itemsIndexed(mouthMood) {index: Int, statsRecord: StatsRecord? ->
            val background = if (statsRecord == null) Colors.Purple20 else Colors.Purple10
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(color = background, shape = RoundedCornerShape(4.dp)).size(18.dp),
            )
        }
    }
}