package com.joohnq.freud_score.ui.presentation.freud_score

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
import com.joohnq.core.ui.presentation.loading.LoadingUI
import com.joohnq.freud_score.ui.components.MentalScoreHistoryItemWithHour
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.mood.components.SharedPanelComponent
import com.joohnq.mood.components.SmallTitle
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.GetStatGroupByDateUseCase
import com.joohnq.mood.state.UiState.Companion.foldComposable
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Drawables
import com.joohnq.mood.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.util.mappers.forEachMap
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.freud_score
import com.joohnq.shared.ui.mental_score_history
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable fun FreudScoreUI(state: FreudScoreState) {
    state.statsRecords.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { statsRecords: List<StatsRecord> ->
            val getStatGroupByDateUseCase: GetStatGroupByDateUseCase = koinInject()
            val mapStatsRecords =
                remember { getStatGroupByDateUseCase(statsRecords) }

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