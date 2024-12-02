package com.joohnq.moodapp.ui.presentation.freud_score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.joohnq.moodapp.ui.components.MentalScoreHistoryItemWithHour
import com.joohnq.moodapp.ui.components.SharedPanelComponent
import com.joohnq.moodapp.ui.components.SmallTitle
import com.joohnq.moodapp.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.moodapp.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.moodapp.ui.presentation.loading.LoadingUI
import com.joohnq.moodapp.ui.state.UiState.Companion.foldComposable
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.helper.StatsManager
import com.joohnq.moodapp.util.mappers.forEachMap
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.mental_score_history
import org.jetbrains.compose.resources.stringResource

@Composable fun FreudScoreUI(state: FreudScoreState) {
    state.statsRecords.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { statsRecords ->
            val mapStatsRecords =
                remember { StatsManager.getGroupByDate(statsRecords) }

            SharedPanelComponent(
                isDark = false,
                onGoBack = { state.onEvent(FreudScoreEvent.OnGoBack) },
                backgroundColor = state.freudScore.palette.backgroundColor,
                backgroundImage = Drawables.Images.FreudScoreBackground,
                panelTitle = Res.string.freud_score,
                bodyTitle = Res.string.mental_score_history,
                color = state.freudScore.palette.subColor,
                onAdd = { state.onEvent(FreudScoreEvent.OnAdd) },
                panelContent = {
                    Column(
                        modifier = Modifier.paddingHorizontalMedium()
                            .padding(top = it.calculateTopPadding()).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = state.freudScore.score.toString(),
                            style = TextStyles.DisplayMdExtraBold(),
                            color = Colors.White
                        )
                        Text(
                            text = stringResource(state.freudScore.title),
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
                            MentalScoreHistoryItemWithHour(
                                statsRecord = statsRecord,
                                onClick = {
                                    state.onEvent(
                                        FreudScoreEvent.OnNavigateToMoodScreen(
                                            statsRecord
                                        )
                                    )
                                },
                            )
                        }
                    }
                }
            )
        }
    )
}