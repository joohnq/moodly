package com.joohnq.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.components.MoodHistoryCard
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.all_history
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodHistoryContent(
    state: MoodHistoryContract.State,
    onEvent: (MoodHistoryContract.Event) -> Unit = {},
    onIntent: (MoodHistoryContract.Intent) -> Unit = {},
) {
    when {
        state.isLoading -> Unit
        state.isError != null -> Unit
        else ->
            SuccessView(
                state = state,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    state: MoodHistoryContract.State,
    onEvent: (MoodHistoryContract.Event) -> Unit,
    onIntent: (MoodHistoryContract.Intent) -> Unit,
) {
    Scaffold(
        containerColor = Colors.Brown10
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .paddingHorizontalMedium()
        ) {
            AppTopBar(
                modifier = Modifier.fillMaxWidth(),
                isDark = true,
                onGoBack = { onEvent(MoodHistoryContract.Event.GoBack) }
            )
            VerticalSpacer(20.dp)
            Text(
                text = stringResource(Res.string.all_history),
                style = TextStyles.textLgBold(),
                color = Colors.Gray80
            )
            VerticalSpacer(20.dp)
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.items) { item ->
                    MoodHistoryCard(
                        item = item,
                        onDelete = { id -> onIntent(MoodHistoryContract.Intent.Delete(id)) }
                    )
                }
            }
        }
    }
}
