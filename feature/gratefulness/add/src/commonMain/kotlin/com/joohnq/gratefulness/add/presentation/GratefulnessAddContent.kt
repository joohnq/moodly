package com.joohnq.gratefulness.add.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.gratefulness.add.presentation.component.GratefulnessTextField
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add
import com.joohnq.shared_resources.add_gratefulness
import com.joohnq.shared_resources.components.BasicAppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.enter_here_what_you_appreciate
import com.joohnq.shared_resources.enter_here_your_gratefulness
import com.joohnq.shared_resources.small_thing_i_appreciate
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.today_i_am_grateful_for
import org.jetbrains.compose.resources.painterResource

@Composable
fun GratefulnessAddContent(
    state: GratefulnessAddContract.State,
    onEvent: (GratefulnessAddContract.Event) -> Unit = {},
    onIntent: (GratefulnessAddContract.Intent) -> Unit = {},
) {
    SuccessView(
        state = state,
        onEvent = onEvent,
        onIntent = onIntent
    )
}

@Composable
private fun SuccessView(
    state: GratefulnessAddContract.State,
    onEvent: (GratefulnessAddContract.Event) -> Unit,
    onIntent: (GratefulnessAddContract.Intent) -> Unit,
) {
    Scaffold(
        containerColor = Colors.Brown5,
        topBar = {
            BasicAppTopBar(
                text = Res.string.add_gratefulness,
                onGoBack = { onEvent(GratefulnessAddContract.Event.GoBack) }
            )
        },
        floatingActionButton = {
            PrimaryButton(
                text = Res.string.add,
                onClick = {
                    onIntent(GratefulnessAddContract.Intent.Add)
                }
            )
        }
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .paddingHorizontalMedium()
                    .padding(vertical = 32.dp)
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Drawables.Images.GratefulnessPot),
                contentDescription = null,
                modifier = Modifier.width(50.dp)
            )
            VerticalSpacer(32.dp)
            GratefulnessTextField(
                label = Res.string.today_i_am_grateful_for,
                value = state.item.iAmGratefulFor,
                placeholder = Res.string.enter_here_your_gratefulness,
                onValueChange = {
                    onIntent(GratefulnessAddContract.Intent.ChangeIAmGratefulFor(it))
                }
            )
            VerticalSpacer(32.dp)
            GratefulnessTextField(
                label = Res.string.small_thing_i_appreciate,
                value = state.item.smallThingIAppreciate,
                placeholder = Res.string.enter_here_what_you_appreciate,
                onValueChange = {
                    onIntent(GratefulnessAddContract.Intent.ChangeSmallThingIAppreciate(it))
                }
            )
            VerticalSpacer(40.dp)
        }
    }
}
