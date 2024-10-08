package com.joohnq.moodapp.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.CustomTextStyle
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.ButtonWithArrowRight
import org.jetbrains.compose.resources.painterResource

@Composable
fun FirstScreen(onGetStarted: () -> Unit, onSignIn: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = CustomTextStyle.TextStyleWelcomeScreenTitle()
                ) {
                    append("Welcome to the ultimate \n")
                }
                withStyle(style = CustomTextStyle.TextStyleWelcomeScreenTitleWord()) {
                    append("freud ")
                }
                withStyle(style = CustomTextStyle.TextStyleWelcomeScreenTitle()) {
                    append("UI Kit!")
                }
            },
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Your mindful mental health AI companion for everyone, anywhere ",
            style = CustomTextStyle.TextStyleWelcomeScreenSubTitle(),
        )
        Spacer(modifier = Modifier.height(32.dp))
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
        Spacer(modifier = Modifier.height(32.dp))
        ButtonWithArrowRight("Get Started", onGetStarted)
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Already have an account?", style = CustomTextStyle.TextStyleWelcomeScreenText())
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.Transparent,
                    contentColor = Colors.Orange40
                ),
                contentPadding = PaddingValues(5.dp),
                onClick = onSignIn
            ) {
                Text("Sign In", style = CustomTextStyle.TextStyleWelcomeScreenButton2())
            }
        }
    }
}
