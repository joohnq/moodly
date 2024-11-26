package com.joohnq.moodapp.ui.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.ContinueButton
import com.joohnq.moodapp.ui.components.TextWithBackground
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.ui.theme.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.assessments
import moodapp.composeapp.generated.resources.page_of
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingBaseComponent(
    page: Int,
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    title: StringResource,
    image: DrawableResource? = null,
    isContinueButtonVisible: Boolean = true,
    onGoBack: () -> Unit,
    onContinue: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalSmall().fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                text = Res.string.assessments,
                onGoBack = onGoBack
            ) {
                TextWithBackground(
                    text = stringResource(Res.string.page_of, page, 7),
                    borderColor = Colors.Transparent,
                    backgroundColor = Colors.Brown20,
                    textColor = Colors.Brown60
                )
            }
            VerticalSpacer(32.dp)
            if (image != null) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.widthIn(max = 300.dp).fillMaxWidth().aspectRatio(1f)
                        .align(alignment = Alignment.CenterHorizontally)
                )
                VerticalSpacer(24.dp)
            }
            Text(
                text = stringResource(title),
                style = TextStyles.HeadingSmExtraBold(),
                color = Colors.Brown80,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            content()
            VerticalSpacer(24.dp)
            if (isContinueButtonVisible) {
                ContinueButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onContinue
                )
                VerticalSpacer(16.dp)
            }
        }
    }
}