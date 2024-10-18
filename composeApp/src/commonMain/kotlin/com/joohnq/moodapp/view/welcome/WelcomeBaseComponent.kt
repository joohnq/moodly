package com.joohnq.moodapp.view.welcome

import androidx.annotation.ColorLong
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.TextWithBackground
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.step
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MockScreen(
    image: DrawableResource,
    step: String,
    index: Int,
    @ColorLong backgroundColor: Color,
    firstTitle: String? = null,
    span: String,
    secondTitle: String? = null,
    @ColorLong spanColor: Color,
    onNext: () -> Unit
) {
    val screenDimensions: ScreenDimensions = koinInject()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = screenDimensions.statusBarHeight.dp),
                contentAlignment = Alignment.Center
            ) {
                TextWithBackground(
                    stringResource(Res.string.step, step),
                    borderColor = Colors.Brown80,
                    backgroundColor = Colors.Transparent,
                    textColor = Colors.Brown80
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f)
                    .background(
                        color = Colors.White,
                    )
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { 0.25f * index },
                    modifier = Modifier.width(180.dp).height(8.dp),
                    color = Colors.Brown80,
                    trackColor = Colors.Brown20,
                    strokeCap = StrokeCap.Round,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = buildAnnotatedString {
                        if (firstTitle != null)
                            withStyle(style = TextStyles.WelcomeScreenTitle()) {
                                append(firstTitle)
                            }
                        withStyle(
                            style = TextStyles.WelcomeScreenTitleWord()
                                .copy(color = spanColor)
                        ) {
                            append(span)
                        }
                        if (secondTitle != null)
                            withStyle(style = TextStyles.WelcomeScreenTitle()) {
                                append(secondTitle)
                            }
                    },
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                ButtonWithArrowRight(
                    modifier = Modifier.size(60.dp), colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.Brown80,
                        contentColor = Colors.White
                    ), onClick = onNext
                )
            }
        }
    }
}
