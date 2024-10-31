package com.joohnq.moodapp.view.screens.freudscore

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.FreudScore
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.HomeTitle
import com.joohnq.moodapp.view.components.MentalScoreHistoryItem
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.TopBarLight
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.UiState.Companion.foldComposable
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.freud_score
import org.jetbrains.compose.resources.painterResource

@Composable
fun FreudScoreScreenUi(
    freudScore: FreudScore,
    statsRecords: UiState<List<StatsRecord>>
) {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth().wrapContentSize()
                .background(color = freudScore.backgroundColor),
        ) {
            Image(
                painter = painterResource(Drawables.Images.FreudScoreBackground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBarLight(Res.string.freud_score, onBack = {})
                Spacer(modifier = Modifier.height(60.dp))
                Text(text = freudScore.score.toString(), style = TextStyles.FreudScreenScore())
                Text(text = freudScore.title, style = TextStyles.FreudScreenTitle())
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
        HomeTitle("Mental Score History")
        statsRecords.foldComposable(
            onLoading = {

            },
            onSuccess = { statsRecords ->
                LazyColumn {
                    items(statsRecords) { statsRecord ->
                        MentalScoreHistoryItem(statsRecord) {}
                    }
                }
            }
        )
    }
}

@Composable
fun FreudScoreScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    moodsViewModel: MoodsViewModel = sharedViewModel(),
) {
    val freudScore by moodsViewModel.freudScore.collectAsState()
    val statsRecords by moodsViewModel.statsRecords.collectAsState()
    FreudScoreScreenUi(
        freudScore = freudScore,
        statsRecords
    )
}

@Preview
@Composable
fun FreudScoreScreenPreview() {
    FreudScoreScreenUi(
        freudScore = FreudScore.fromScore(80),
        statsRecords = UiState.Success(
            listOf(
                StatsRecord.init()
                    .copy(description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
            )
        )
    )
}

