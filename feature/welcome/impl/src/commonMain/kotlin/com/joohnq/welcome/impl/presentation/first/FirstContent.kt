package com.joohnq.welcome.impl.presentation.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.LogoWithBackground
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.text.HeadingWithSpan
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FirstContent(
    onNext: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().paddingHorizontalMedium()
    ) {
        LogoWithBackground()
        VerticalSpacer(10.dp)
        HeadingWithSpan(
            firstTitle = Res.string.first_screen_title,
            secondTitle = Res.string.first_screen_second_title,
            span = stringResource(Res.string.first_screen_title_word),
            spanColor = Colors.Brown60,
        )
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(Res.string.first_screen_desc),
            style = TextStyles.paragraphLg(),
            color = Colors.Brown100Alpha64,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(32.dp)
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(color = Colors.White, shape = Dimens.Shape.Circle)
            )
            Image(
                painter = painterResource(Drawables.Images.WelcomeFirstScreen),
                contentDescription = stringResource(Res.string.first_screen_image),
            )
        }
        VerticalSpacer(32.dp)
        PrimaryButton(
            text = Res.string.get_started,
            onClick = onNext
        )
        VerticalSpacer(30.dp)
    }
}