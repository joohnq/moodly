package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.core.ui.mapper.forEachMap
import com.joohnq.core.ui.mapper.toFormattedDateString
import com.joohnq.freud_score.ui.components.MentalScoreHistoryItemWithHour
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.viewmodel.FreudScoreState
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.GetStatGroupByDateUseCase
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.SmallTitle
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.mental_score_history
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun FreudScoreUI(
    state: FreudScoreState,
    statsRecords: UiState<List<StatsRecord>>,
    onEvent: (FreudScoreEvent) -> Unit = {},
) {
    val freudScore = state.freudScore!!
    statsRecords.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { statsRecords: List<StatsRecord> ->
            val getStatGroupByDateUseCase: GetStatGroupByDateUseCase = koinInject()
            val mapStatsRecords =
                remember { getStatGroupByDateUseCase(statsRecords) }

            SharedPanelComponent(
                isDark = false,
                onGoBack = { onEvent(FreudScoreEvent.GoBack) },
                backgroundColor = freudScore.palette.backgroundColor,
                backgroundImage = Drawables.Images.FreudScoreBackground,
                panelTitle = Res.string.freud_score,
                bodyTitle = Res.string.mental_score_history,
                color = freudScore.palette.subColor,
                onAdd = { onEvent(FreudScoreEvent.Add) },
                panelContent = {
                    Column(
                        modifier = Modifier.paddingHorizontalMedium()
                            .padding(top = it.calculateTopPadding()).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(
                                12.dp,
                                alignment = Alignment.CenterHorizontally
                            )
                        ) {
                            Icon(
                                painter = painterResource(Drawables.Icons.Logo),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = freudScore.palette.subColor
                            )
                            Text(
                                text = freudScore.score.toString(),
                                style = TextStyles.DisplayMdExtraBold(),
                                color = freudScore.palette.subColor
                            )
                        }
                        Text(
                            text = stringResource(freudScore.title),
                            style = TextStyles.TextXlSemiBold(),
                            color = freudScore.palette.subColor
                        )
                    }
                },
                content = {
                    mapStatsRecords.forEachMap { date, items ->
                        item {
                            SmallTitle(text = date.toFormattedDateString())
                        }
                        items(items) { statsRecord ->
                            val resource = statsRecord.mood.toResource()
                            MentalScoreHistoryItemWithHour(
                                date = statsRecord.createdAt,
                                resource = resource,
                                description = statsRecord.description,
                                healthLevel = resource.healthLevel,
                                onClick = {
                                    onEvent(
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