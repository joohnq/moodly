package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.components.ButtonWithArrowOpen
import com.joohnq.moodapp.components.CustomTextStyle
import com.joohnq.moodapp.components.TextWithBackground

@Composable
fun OnboardingTopBar(page: Int){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ButtonWithArrowOpen {}
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                "Assessments",
                style = CustomTextStyle.TextStyleOnboardingScreenSession()
            )
        }
        TextWithBackground(
            "$page of 7",
            borderColor = Colors.Transparent,
            backgroundColor = Colors.Brown20,
            textColor = Colors.Brown60
        )
    }
}