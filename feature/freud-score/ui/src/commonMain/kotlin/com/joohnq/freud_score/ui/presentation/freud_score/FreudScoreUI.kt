package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.core.ui.mapper.forEachMapComposable
import com.joohnq.core.ui.mapper.toFormattedDateString
import com.joohnq.freud_score.ui.components.MentalScoreHistoryItemWithHour
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.presentation.freud_score.event.toFreudScoreEvent
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.freud_score.ui.viewmodel.FreudScoreState
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.use_case.GetStatGroupByDateUseCase
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.viewmodel.StatsState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.LoadingUI
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.SmallTitle
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun FreudContent(
    records: Map<LocalDate, List<StatsRecord>>,
    onEvent: (FreudScoreEvent) -> Unit
) {
    Column {
        records.forEachMapComposable { date, items ->
            SmallTitle(text = date.toFormattedDateString())
            LazyColumn(modifier = Modifier.wrapContentHeight()) {
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
    }

}

@Composable
fun FreudPanel(
    freudScore: FreudScoreResource
) {
    Column(
        modifier = Modifier.paddingHorizontalMedium().fillMaxSize(),
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
                tint = freudScore.palette.color
            )
            Text(
                text = freudScore.score.toString(),
                style = TextStyles.DisplayMdExtraBold(),
                color = freudScore.palette.color
            )
        }
        Text(
            text = stringResource(freudScore.title),
            style = TextStyles.TextXlSemiBold(),
            color = freudScore.palette.color
        )
    }
}

@Composable
fun FreudScoreUI(
    state: FreudScoreState,
    statsState: StatsState,
    onEvent: (FreudScoreEvent) -> Unit = {},
) {
    statsState.statsRecords.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { statsRecords: List<StatsRecord> ->
            val getStatGroupByDateUseCase: GetStatGroupByDateUseCase = koinInject()
            val recordsMap =
                remember { getStatGroupByDateUseCase(statsRecords) }

            SharedPanelComponent(
                isDark = false,
                paddingValues = Dimens.Padding.HorizontalMedium,
                backgroundColor = state.freudScore!!.palette.backgroundColor,
                image = Drawables.Images.FreudScoreBackground,
                title = Res.string.freud_score,
                color = state.freudScore.palette.imageColor,
                onEvent = { event -> event.toFreudScoreEvent() },
                panel = { FreudPanel(state.freudScore) },
                content = {
                    FreudContent(
                        records = recordsMap,
                        onEvent = onEvent
                    )
                }
            )
        }
    )
}