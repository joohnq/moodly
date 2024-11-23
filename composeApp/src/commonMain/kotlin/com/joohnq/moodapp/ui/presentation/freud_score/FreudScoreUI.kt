package com.joohnq.moodapp.ui.presentation.freud_score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joohnq.moodapp.ui.components.MentalScoreHistoryItemWithHour
import com.joohnq.moodapp.ui.components.SharedPanelComponent
import com.joohnq.moodapp.ui.components.SmallTitle
import com.joohnq.moodapp.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.moodapp.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.mappers.forEachMap
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.mental_score_history
import org.jetbrains.compose.resources.stringResource

@Composable fun FreudScoreUI(
    state: FreudScoreState,
) {
    val (freudScore, mapStatsRecords, onAction) = state
    SharedPanelComponent(
        isDark = false,
        onGoBack = { onAction(FreudScoreEvent.OnGoBack) },
        backgroundColor = freudScore.palette.backgroundColor,
        backgroundImage = Drawables.Images.FreudScoreBackground,
        panelTitle = Res.string.freud_score,
        bodyTitle = Res.string.mental_score_history,
        color = freudScore.palette.subColor,
        onAdd = { onAction(FreudScoreEvent.OnAdd) },
        panelContent = {
            Column(
                modifier = Modifier.paddingHorizontalMedium()
                    .padding(top = it.calculateTopPadding()).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = freudScore.score.toString(),
                    style = TextStyles.DisplayMdExtraBold(),
                    color = Colors.White
                )
                Text(
                    text = stringResource(freudScore.title),
                    style = TextStyles.TextXlSemiBold(),
                    color = Colors.White
                )
            }
        },
        content = {
            mapStatsRecords.forEachMap { key, items ->
                item {
                    SmallTitle(text = key)
                }
                items(items) { statsRecord ->
                    MentalScoreHistoryItemWithHour(statsRecord) {
                        onAction(
                            FreudScoreEvent.OnNavigateToMoodScreen(
                                statsRecord
                            )
                        )
                    }
                }
            }
        }
    )
}