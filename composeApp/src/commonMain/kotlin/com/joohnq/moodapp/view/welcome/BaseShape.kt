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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
import com.joohnq.moodapp.ScreenDimensions
import com.joohnq.moodapp.view.components.CustomTextStyle
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
                    borderColor = CustomColors.Brown80,
                    backgroundColor = CustomColors.Transparent,
                    textColor = CustomColors.Brown80
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f)
                    .background(
                        color = CustomColors.White,
                    )
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { 0.25f * index },
                    modifier = Modifier.width(180.dp).height(8.dp),
                    color = CustomColors.Brown80,
                    trackColor = CustomColors.Brown20,
                    strokeCap = StrokeCap.Round,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = buildAnnotatedString {
                        if (firstTitle != null)
                            withStyle(style = CustomTextStyle.TextStyleWelcomeScreenTitle()) {
                                append(firstTitle)
                            }
                        withStyle(
                            style = CustomTextStyle.TextStyleWelcomeScreenTitleWord()
                                .copy(color = spanColor)
                        ) {
                            append(span)
                        }
                        if (secondTitle != null)
                            withStyle(style = CustomTextStyle.TextStyleWelcomeScreenTitle()) {
                                append(secondTitle)
                            }
                    },
                    lineHeight = 40.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.size(80.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomColors.Brown80,
                        contentColor = CustomColors.White
                    ),
                    shape = CircleShape,
                    onClick = onNext
                ) {
                    Icon(
                        painter = painterResource(CustomDrawables.Icons.Arrow),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
