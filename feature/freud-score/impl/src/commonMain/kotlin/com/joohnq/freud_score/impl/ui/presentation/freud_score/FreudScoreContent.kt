package com.joohnq.freud_score.impl.ui.presentation.freud_score

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.freud_score.impl.ui.ProgressiveDottedCircles
import com.joohnq.freud_score.impl.ui.mapper.FreudScoreResourceMapper.allFreudScoreResources
import com.joohnq.freud_score.impl.ui.mapper.FreudScoreResourceMapper.toEndFreudScore
import com.joohnq.freud_score.impl.ui.mapper.FreudScoreResourceMapper.toInitialFreudScore
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.ColoredIndicatorItem
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.freud_score
import com.joohnq.shared_resources.hyphen
import com.joohnq.shared_resources.out_of_100
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun FreudScoreContent(
    state: FreudScoreContract.State,
    onEvent: (FreudScoreContract.Event) -> Unit = {},
) {
    if (state.freudScore == null) return
    val resources = remember { allFreudScoreResources(state.freudScore.score) }

    Scaffold(
        containerColor = Colors.Brown10
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            AppTopBar(
                modifier = Modifier.paddingHorizontalMedium(),
                isDark = true,
                text = Res.string.freud_score,
                onGoBack = { onEvent(FreudScoreContract.Event.OnGoBack) }
            )
            Column(
                modifier = Modifier.paddingHorizontalMedium(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val color = state.freudScore.palette.backgroundColor

                ProgressiveDottedCircles(
                    modifier = Modifier.sizeIn(maxWidth = 500.dp, maxHeight = 500.dp),
                    color = color
                ) {
                    Text(
                        text = state.freudScore.score.toString(),
                        style = TextStyles.headingXlBold(),
                        color = state.freudScore.palette.backgroundColor
                    )
                    Text(
                        text = stringResource(Res.string.out_of_100),
                        style = TextStyles.textSmRegular(),
                        color = Colors.Gray60
                    )
                }
                VerticalSpacer(24.dp)
                Text(
                    text = stringResource(state.freudScore.subtitle),
                    style = TextStyles.paragraphLg(),
                    color = Colors.Gray80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(12.dp)
                resources.forEachIndexed { i, resource ->
                    ColoredIndicatorItem(
                        color = resource.palette.backgroundColor,
                        title =
                            stringResource(
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
