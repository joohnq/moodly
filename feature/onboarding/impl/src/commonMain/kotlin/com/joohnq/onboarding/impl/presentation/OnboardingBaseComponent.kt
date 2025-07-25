package com.joohnq.onboarding.impl.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.assessments
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.page_of
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingBaseComponent(
    page: Int,
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    title: StringResource,
    image: DrawableResource? = null,
    isContinueButtonVisible: Boolean = true,
    onGoBack: () -> Unit,
    onContinue: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    AppScaffoldLayout(
        snackBarHostState = snackBarState,
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).paddingHorizontalMedium().fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(
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
                style = TextStyles.headingSmExtraBold(),
                color = Colors.Brown80,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            content()
            VerticalSpacer(24.dp)
            if (isContinueButtonVisible) {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.continue_word,
                    onClick = onContinue
                )
                VerticalSpacer(16.dp)
            }
        }
    }
}