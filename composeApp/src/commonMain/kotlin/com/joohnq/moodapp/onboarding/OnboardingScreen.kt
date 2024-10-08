package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.joohnq.moodapp.ButtonWithArrowOpen
import com.joohnq.moodapp.CustomTextStyle
import org.jetbrains.compose.ui.tooling.preview.Preview

class OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        Scaffold { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        ButtonWithArrowOpen {}
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            "Assessments",
                            style = CustomTextStyle.TextStyleOnboardingScreenSession()
                        )
                    }
                }
            }
        }
    }
}