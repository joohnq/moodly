package com.joohnq.moodapp.view.screens.freudscore

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.MentalScoreHistoryItem
import com.joohnq.moodapp.view.components.PanelContentLight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.routes.onNavigateToMood
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.StatsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import moodapp.composeapp.generated.resources.mental_score_history
import org.jetbrains.compose.resources.stringResource

@Composable
fun FreudScoreScreenUi(
    freudScore: FreudScore,
    statsRecords: List<StatsRecord>,
    onAction: (FreudScoreAction) -> Unit = {}
) {
    Scaffold(
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        Column {
            PanelContentLight(
                padding = padding,
                text = Res.string.freud_score,
                backgroundColor = freudScore.palette.backgroundColor,
                background = Drawables.Images.FreudScoreBackground,
                color = freudScore.palette.subColor,
                onAdd = {},
                onGoBack = { onAction(FreudScoreAction.OnGoBack) }
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = padding.calculateTopPadding()).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = freudScore.score.toString(), style = TextStyles.FreudScreenScore())
                    Text(
                        text = stringResource(freudScore.title),
                        style = TextStyles.FreudScreenTitle()
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Title(Res.string.mental_score_history)
            LazyColumn {
                items(statsRecords) { statsRecord ->
                    MentalScoreHistoryItem(statsRecord) {
                        onAction(
                            FreudScoreAction.OnNavigateToMood(
                                statsRecord
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FreudScoreScreen(
    statsViewModel: StatsViewModel = sharedViewModel(),
    navigation: NavHostController,
) {
    val moodsState by statsViewModel.statsState.collectAsState()

    FreudScoreScreenUi(
        freudScore = moodsState.freudScore,
        statsRecords = moodsState.statsRecords.getValue(),
        onAction = {
            when (it) {
                FreudScoreAction.OnGoBack -> navigation.popBackStack()
                is FreudScoreAction.OnNavigateToMood -> navigation.onNavigateToMood(it.statsRecord)
            }
        }
    )
}

@Preview
@Composable
fun FreudScoreScreenPreview() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(80),
        statsRecords = listOf(
            StatsRecord.init()
                .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
    )
}

@Preview
@Composable
fun FreudScoreScreenPreview2() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(60),
        statsRecords = listOf(
            StatsRecord.init()
                .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
    )
}

@Preview
@Composable
fun FreudScoreScreenPreview3() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(40),
        statsRecords = listOf(
            StatsRecord.init()
                .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
    )
}

@Preview
@Composable
fun FreudScoreScreenPreview4() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(20),
        statsRecords = listOf(
            StatsRecord.init()
                .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
    )
}

@Preview
@Composable
fun FreudScoreScreenPreview5() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(0),
        statsRecords = listOf(
            StatsRecord.init()
                .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
        )
    )
}

