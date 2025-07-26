package com.joohnq.shared_resources.components.input_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.write_what_are_you_feeling
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExpressionAnalysisTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        label = null,
        shape = Dimens.Shape.Large,
        placeholder = {
            Text(
                text = stringResource(Res.string.write_what_are_you_feeling),
                style = TextStyles.text2xlBold(),
                color = Colors.Brown100Alpha64
            )
        },
        modifier =
            modifier
                .fillMaxWidth()
                .heightIn(min = 250.dp)
                .shadow(
                    elevation = 7.dp,
                    ambientColor = Colors.Black48.copy(alpha = 0.2f),
                    spotColor = Colors.Black48.copy(alpha = 0.2f),
                    shape = Dimens.Shape.Large
                ),
        colors = ComponentColors.TextField.expressionAnalysisColors(),
        textStyle = TextStyles.text2xlBold()
    )
}
