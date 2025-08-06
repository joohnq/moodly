package com.joohnq.stress_level.add.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_stress_level
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.BubbleText
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.select_stressors
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.impl.ui.mapper.StressorResourceMapper.allStressorResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressStressorsContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: AddStressLevelContract.State,
    onIntent: (AddStressLevelContract.Intent) -> Unit = {},
    onEvent: (AddStressLevelContract.Event) -> Unit = {},
) {
    val stressors = remember { allStressorResource() }
    val canContinue by derivedStateOf { state.item.stressors.isNotEmpty() }

    AppScaffoldLayout(
        snackBarHostState = snackBarState,
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .paddingHorizontalMedium()
                    .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(
                text = Res.string.add_stress_level,
                onGoBack = { onEvent(AddStressLevelContract.Event.GoBack) }
            )
            VerticalSpacer(60.dp)
            Text(
                text = stringResource(Res.string.select_stressors),
                style = TextStyles.headingSmExtraBold(),
                color = Colors.Brown80,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(minSize = 100.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                content = {
                    items(stressors) { stressor ->
                        BubbleText(
                            text = stressor.text,
                            onClick = {
                                onIntent(
                                    AddStressLevelContract.Intent.ChangeAddingStressors(stressor)
                                )
                            },
                            selected =
                                state.item.stressors.contains(
                                    stressor
                                )
                        )
                    }
                }
            )
            VerticalSpacer(24.dp)
            if (canContinue) {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.continue_word,
                    onClick = { onIntent(AddStressLevelContract.Intent.Add) }
                )
            }
        }
    }
}