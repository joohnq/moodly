package com.joohnq.welcome.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared.ui.components.ContinueButton
import com.joohnq.shared.ui.components.TextWithSpan
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.first_screen_desc
import com.joohnq.shared.ui.first_screen_image
import com.joohnq.shared.ui.first_screen_second_title
import com.joohnq.shared.ui.first_screen_title
import com.joohnq.shared.ui.first_screen_title_word
import com.joohnq.shared.ui.get_started
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FirstScreen(onGetStarted: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().paddingHorizontalMedium()
    ) {
        TextWithSpan(
            firstTitle = stringResource(Res.string.first_screen_title),
            secondTitle = stringResource(Res.string.first_screen_second_title),
            span = stringResource(Res.string.first_screen_title_word),
            spanColor = Colors.Brown60,
        )
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(Res.string.first_screen_desc),
            style = TextStyles.ParagraphLg(),
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
                painter = painterResource(Drawables.Images.WelcomeFirstScreenImage),
                contentDescription = stringResource(Res.string.first_screen_image),
            )
        }
        VerticalSpacer(32.dp)
        ContinueButton(text = Res.string.get_started, onClick = onGetStarted)
        VerticalSpacer(30.dp)
    }
}
