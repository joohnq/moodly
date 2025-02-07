package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.freud_score.ui.mapper.getAllFreudScoreResources
import com.joohnq.freud_score.ui.mapper.toEndFreudScore
import com.joohnq.freud_score.ui.mapper.toInitialFreudScore
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.freud_score.ui.viewmodel.FreudScoreState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.BallItem
import com.joohnq.shared_resources.components.DottedCircles
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.hyphen
import com.joohnq.shared_resources.out_of_100
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FreudScoreUI(
    state: FreudScoreState,
    onEvent: (FreudScoreEvent) -> Unit = {},
) {
    if (state.freudScore == null) return
    val resources = remember { getAllFreudScoreResources(state.freudScore.score) }

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopBar(
                modifier = Modifier.paddingHorizontalMedium(),
                isDark = true,
                text = Res.string.freud_score,
                onGoBack = { onEvent(FreudScoreEvent.OnGoBack) },
            )
            Column(
                modifier = Modifier.paddingHorizontalMedium(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val color = state.freudScore.palette.backgroundColor

                DottedCircles(
                    color = color
                ) {
                    Text(
                        text = state.freudScore.score.toString(),
                        style = TextStyles.HeadingXlBold(),
                        color = state.freudScore.palette.backgroundColor
                    )
                    Text(
                        text = stringResource(Res.string.out_of_100),
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
                    )
                }
                VerticalSpacer(24.dp)
                Text(
                    text = stringResource(state.freudScore.subtitle),
                    style = TextStyles.ParagraphLg(),
                    color = Colors.Gray80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(12.dp)
                resources.forEachIndexed { i, resource ->
                    BallItem(
                        color = resource.palette.backgroundColor,
                        title = stringResource(
                            Res.string.hyphen,
                            i.toEndFreudScore(),
                            i.toInitialFreudScore()
                        ),
                        description = stringResource(resource.title),
                        isNotLast = i < resources.lastIndex
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FreudScoreUIPreviewUnhealthy() {
    FreudScoreUI(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Unhealthy(10)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewAtRisk() {
    FreudScoreUI(
        state = FreudScoreState(
            freudScore = FreudScoreResource.AtRisk(30)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewStable() {
    FreudScoreUI(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Stable(50)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewMostlyHealthy() {
    FreudScoreUI(
        state = FreudScoreState(
            freudScore = FreudScoreResource.MostlyHealthy(70)
        ),
    )
}

@Preview
@Composable
fun FreudScoreUIPreviewHealthy() {
    FreudScoreUI(
        state = FreudScoreState(
            freudScore = FreudScoreResource.Healthy(90)
        ),
    )
}