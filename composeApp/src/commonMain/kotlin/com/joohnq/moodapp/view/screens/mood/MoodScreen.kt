package com.joohnq.moodapp.view.screens.mood

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.MoodsManager
import com.joohnq.moodapp.entities.Icon
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ButtonWithIcon
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.TopBarDark
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.StatsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.next
import moodapp.composeapp.generated.resources.previous
import moodapp.composeapp.generated.resources.your_mood_is
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodScreenUi(
    statsRecord: StatsRecord,
    hasNext: Boolean,
    hasPrevious: Boolean,
    onPrevious: () -> Unit = {},
    onNext: () -> Unit = {},
    onAction: (MoodAction) -> Unit = {}
) {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth().wrapContentSize()
                .background(color = statsRecord.mood.palette.backgroundColor),
        ) {
            Box(
                modifier = Modifier.matchParentSize().paint(
                    painter = painterResource(Drawables.Images.MoodBackground),
                    contentScale = ContentScale.FillBounds,
                    colorFilter = ColorFilter.tint(color = statsRecord.mood.palette.subColor)
                )
            )
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBarDark(Res.string.mood, onGoBack = { onAction(MoodAction.OnGoBack) })
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = stringResource(Res.string.your_mood_is), style = TextStyles.BoldXL(),
                    color = statsRecord.mood.palette.color
                )
                Text(
                    text = stringResource(statsRecord.mood.text),
                    style = TextStyles.ExtraBold2XL(),
                    color = statsRecord.mood.palette.color
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (hasPrevious)
                        ButtonWithIcon(
                            onClick = onPrevious,
                            icon = Icon(
                                icon = Drawables.Icons.ArrowChevron,
                                modifier = Modifier.size(24.dp),
                                tint = statsRecord.mood.palette.color,
                                contentDescription = Res.string.previous
                            ),
                            colors = ButtonColors(
                                containerColor = Colors.Transparent,
                                contentColor = statsRecord.mood.palette.color,
                                disabledContainerColor = Colors.Transparent,
                                disabledContentColor = statsRecord.mood.palette.color,
                            ),
                            modifier = Modifier.size(48.dp),
                        )
                    MoodFace(modifier = Modifier.size(96.dp), mood = statsRecord.mood)
                    if (hasNext)
                        ButtonWithIcon(
                            onClick = onNext,
                            icon = Icon(
                                icon = Drawables.Icons.ArrowChevron,
                                modifier = Modifier.size(24.dp).rotate(180f),
                                tint = statsRecord.mood.palette.color,
                                contentDescription = Res.string.next
                            ),
                            colors = ButtonColors(
                                containerColor = Colors.Transparent,
                                contentColor = statsRecord.mood.palette.color,
                                disabledContainerColor = Colors.Transparent,
                                disabledContentColor = statsRecord.mood.palette.color,
                            ),
                            modifier = Modifier.size(48.dp),
                        )
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}

@Composable
fun MoodScreen(
    statsRecord: StatsRecord,
    statsViewModel: StatsViewModel = sharedViewModel(),
    onAction: (MoodAction) -> Unit
) {
    val moodsState by statsViewModel.statsState.collectAsState()
    var hasNext by remember { mutableStateOf<StatsRecord?>(null) }
    var hasPrevious by remember { mutableStateOf<StatsRecord?>(null) }
    var currentStatsRecord by remember { mutableStateOf(statsRecord) }

    LaunchedEffect(currentStatsRecord) {
        hasNext = MoodsManager.getNext(currentStatsRecord, moodsState.statsRecords.getValue())
        hasPrevious =
            MoodsManager.getPrevious(currentStatsRecord, moodsState.statsRecords.getValue())
    }

    MoodScreenUi(
        statsRecord = currentStatsRecord,
        hasNext = hasNext != null,
        hasPrevious = hasPrevious != null,
        onAction = onAction,
        onPrevious = {
            if (hasPrevious != null) {
                currentStatsRecord = hasPrevious as StatsRecord
            }
        },
        onNext = {
            if (hasNext != null) {
                currentStatsRecord = hasNext as StatsRecord
            }
        }
    )
}

@Preview
@Composable
fun MoodScreenPreview() {
    MoodScreenUi(statsRecord = StatsRecord.init(), hasPrevious = true, hasNext = true)
}
