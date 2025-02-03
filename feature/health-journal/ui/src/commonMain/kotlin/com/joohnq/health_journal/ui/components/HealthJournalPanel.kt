package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.journals_this_year
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun HealthJournalPanel(
    dayPerYear: String
) {
    Column(
        modifier = Modifier.paddingHorizontalMedium().fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Book),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = Colors.Brown20
            )
            Text(
                text = dayPerYear,
                style = TextStyles.Heading2xlExtraBold(),
                color = Colors.Brown20
            )
        }
        VerticalSpacer(10.dp)
        Text(
            text = stringResource(Res.string.journals_this_year),
            style = TextStyles.TextXlSemiBold(),
            color = Colors.Brown20
        )
    }
}