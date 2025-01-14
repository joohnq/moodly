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
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.freud_score.ui.components.MentalScoreHistoryItemWithHour
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.GetStatGroupByDateUseCase
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.moodapp.presentation.loading.LoadingUI
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.SmallTitle
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.mental_score_history
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.util.mappers.forEachMap
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
                onGoBack = { state.onEvent(FreudScoreEvent.GoBack) },
                backgroundColor = state.freudScore.palette.backgroundColor,
                backgroundImage = Drawables.Images.FreudScoreBackground,
                panelTitle = Res.string.freud_score,
                bodyTitle = Res.string.mental_score_history,
                color = state.freudScore.palette.subColor,
                onAdd = { state.onEvent(FreudScoreEvent.Add) },
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
                            SmallTitle(text = DatetimeProvider.formatDate(key))
                        }
                        items(items) { statsRecord ->
                            val resource = statsRecord.mood.toResource()
                            MentalScoreHistoryItemWithHour(
                                date = statsRecord.date,
                                resource = resource,
                                description = statsRecord.description,
                                healthLevel = resource.healthLevel,
                                onClick = {
                                    state.onEvent(
                                        FreudScoreEvent.NavigateToMoodScreen(
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