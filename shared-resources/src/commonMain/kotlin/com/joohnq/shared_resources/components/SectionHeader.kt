package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.see_more
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_mood_is
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: StringResource,
    onSeeMore: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            style = TextStyles.TextLgExtraBold(),
            color = Colors.Brown80,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        TextButton(
            onClick = onSeeMore,
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 2.dp),
            shape = Dimens.Shape.Circle,
            colors = ButtonColors(
                containerColor = Colors.Transparent,
                contentColor = Colors.Brown80,
                disabledContainerColor = Colors.Transparent,
                disabledContentColor = Colors.Brown80
            )
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(Res.string.see_more),
                style = TextStyles.TextSmMedium(),
            )
        }
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: StringResource,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            style = TextStyles.TextLgExtraBold(),
            color = Colors.Brown80,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}

@Preview
@Composable
fun SectionHeaderPreview() {
    SectionHeader(
        title = Res.string.your_mood_is,
        onSeeMore = {}
    )
}