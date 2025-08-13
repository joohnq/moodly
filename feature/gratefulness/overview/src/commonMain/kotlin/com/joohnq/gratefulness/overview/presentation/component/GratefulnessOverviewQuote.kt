package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.gratefulness.api.entity.Quote
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.quotes
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource

@Composable
fun GratefulnessOverviewQuote(
    modifier: Modifier = Modifier,
    quote: Quote,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.quotes
    )
    Card(
        modifier = modifier,
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Color.Unspecified,
                disabledContentColor = Colors.Gray5,
                disabledContainerColor = Color.Unspecified
            ),
        shape = Dimens.Shape.ExtraLarge
    ) {
        Column(
            modifier = Modifier.paddingAllSmall(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Filled.Quotes),
                contentDescription = null,
                tint = Colors.Green40
            )
            Text(
                text = "\"${quote.content}\"",
                style = TextStyles.headingXsRegular(),
                color = Colors.Gray80
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "\\u2014${quote.author}",
                    style = TextStyles.headingXsRegular(),
                    color = Colors.Gray80
                )
            }
        }
    }
}
