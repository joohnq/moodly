package com.joohnq.moodapp.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.onboarding.ExpressionAnalysisScreen

@Composable
fun ExpressionAnalysisTextField(text: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = null,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(min = 250.dp).border(
                color = Colors.Brown80Alpha25,
                width = 4.dp,
                shape = RoundedCornerShape(20.dp)
            ),
        colors = TextFieldColors(
            indicatorColor = Colors.Orange40,
            containerColor = Colors.White,
            textColor = Colors.Brown80,
            placeholderColor = Colors.Alpha100,
            cursorColor = Colors.Orange40,
        ),
        textStyle = TextStyles.ExpressionAnalysisTextField()
    )
}