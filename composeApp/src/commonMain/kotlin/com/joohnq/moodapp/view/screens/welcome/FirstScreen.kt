package com.joohnq.moodapp.view.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.first_screen_desc
import moodapp.composeapp.generated.resources.first_screen_second_title
import moodapp.composeapp.generated.resources.first_screen_title
import moodapp.composeapp.generated.resources.first_screen_title_word
import moodapp.composeapp.generated.resources.get_started
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun FirstScreen(onGetStarted: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = TextStyles.HeadingSpanSmExtraBold().copy(color = Colors.Brown80),
                ) {
                    append(stringResource(Res.string.first_screen_title))
                }
                withStyle(
                    style = TextStyles.HeadingSpanSmExtraBold().copy(color = Colors.Brown60)
                ) {
                    append(stringResource(Res.string.first_screen_title_word))
                }
                withStyle(
                    style = TextStyles.HeadingSpanSmExtraBold().copy(color = Colors.Brown80)
                ) {
                    append(stringResource(Res.string.first_screen_second_title))
                }
            },
            textAlign = TextAlign.Center
        )
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(Res.string.first_screen_desc),
            style = TextStyles.ParagraphLg(),
            color = Colors.Brown100Alpha64
        )
        VerticalSpacer(32.dp)
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(color = Colors.White, shape = CircleShape)
            )
            Image(
                painter = painterResource(Drawables.Images.WelcomeFirstScreenImage),
                contentDescription = null,
            )
        }
        VerticalSpacer(32.dp)
        ButtonWithArrowRight(text = Res.string.get_started, onClick = onGetStarted)
        VerticalSpacer(30.dp)
    }
}
