package com.joohnq.moodapp.view.screens.freudscore

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.StatsManager
import com.joohnq.moodapp.mappers.forEachMap
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.MentalScoreHistoryItemWithHour
import com.joohnq.moodapp.view.components.SharedPanelComponent
import com.joohnq.moodapp.view.components.SmallTitle
import com.joohnq.moodapp.view.routes.onNavigateToAddMood
import com.joohnq.moodapp.view.routes.onNavigateToMood
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StatsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.mental_score_history
import org.jetbrains.compose.resources.stringResource

@Composable fun FreudScoreScreenUi(
    freudScore: FreudScore,
    mapStatsRecords: Map<String, List<StatsRecord>>,
    onAction: (FreudScoreAction) -> Unit = {}
) {
    SharedPanelComponent(
        isDark = false,
        onGoBack = { onAction(FreudScoreAction.OnGoBack) },
        backgroundColor = freudScore.palette.backgroundColor,
        backgroundImage = Drawables.Images.FreudScoreBackground,
        panelTitle = Res.string.freud_score,
        bodyTitle = Res.string.mental_score_history,
        color = freudScore.palette.subColor,
        onAdd = { onAction(FreudScoreAction.OnAdd) },
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
                            FreudScoreAction.OnNavigateToMood(
                                statsRecord
                            )
                        )
                    }
                }
            }
        }
    )
}

@Composable fun FreudScoreScreen(
    statsViewModel: StatsViewModel = sharedViewModel(),
    navigation: NavHostController,
) {
    val statsState by statsViewModel.statsState.collectAsState()
    val mapStatsRecords =
        remember { StatsManager.getGroupByDate(statsState.statsRecords.getValue()) }

    FreudScoreScreenUi(
        freudScore = statsState.freudScore,
        mapStatsRecords = mapStatsRecords,
        onAction = {
            when (it) {
                is FreudScoreAction.OnGoBack -> navigation.popBackStack()
                is FreudScoreAction.OnNavigateToMood -> navigation.onNavigateToMood(it.statsRecord.id)
                is FreudScoreAction.OnAdd -> navigation.onNavigateToAddMood()
            }
        }
    )
}

@Preview @Composable fun FreudScoreScreenPreview() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(80),
        mapStatsRecords = mapOf(
            "" to listOf(
                StatsRecord.init()
                    .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            )
        )
    )
}

@Preview @Composable fun FreudScoreScreenPreview2() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(60),
        mapStatsRecords = mapOf(
            "" to listOf(
                StatsRecord.init()
                    .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            )
        )
    )
}

@Preview @Composable fun FreudScoreScreenPreview3() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(40),
        mapStatsRecords = mapOf(
            "" to listOf(
                StatsRecord.init()
                    .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            )
        )
    )
}

@Preview @Composable fun FreudScoreScreenPreview4() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(20),
        mapStatsRecords = mapOf(
            "" to listOf(
                StatsRecord.init()
                    .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            )
        )
    )
}

@Preview @Composable fun FreudScoreScreenPreview5() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(0),
        mapStatsRecords = mapOf(
            "" to listOf(
                StatsRecord.init()
                    .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            )
        )
    )
}

