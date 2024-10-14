package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.OnboardingTopBar
import com.joohnq.moodapp.view.components.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingBaseComponent(
    page: Int,
    title: StringResource,
    image: DrawableResource? = null,
    isContinueButtonVisible: Boolean = true,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingTopBar(page = page, onBack = onBack)
            Spacer(modifier = Modifier.height(32.dp))
            if (image != null) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.widthIn(max = 300.dp).fillMaxWidth().aspectRatio(1f)
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            Text(
                text = stringResource(title),
                style = TextStyles.OnboardingScreenTitle()
            )
            Spacer(modifier = Modifier.height(24.dp))
            content()
            Spacer(modifier = Modifier.height(16.dp))
            if (isContinueButtonVisible) {
                ButtonWithArrowRight(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.continue_word),
                    onClick = onContinue
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}