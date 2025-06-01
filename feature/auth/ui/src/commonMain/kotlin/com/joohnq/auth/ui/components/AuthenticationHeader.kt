package com.joohnq.auth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.components.Logo
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.welcome_authentication_welcome_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthenticationHeader(
    modifier: Modifier = Modifier,
    text: String
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VerticalSpacer(32.dp)
        Logo(
            modifier = Modifier.size(48.dp),
            tint = Colors.Brown60
        )
        VerticalSpacer(12.dp)
        Text(
            text = stringResource(Res.string.app_name).lowercase(),
            style = TextStyles.headingMdBold(),
            color = Colors.Brown90,
            textAlign = TextAlign.Center
        )
        VerticalSpacer(16.dp)
        Text(
            text = text,
            style = TextStyles.textMdRegular(),
            color = Colors.Gray60,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AuthenticationWelcomeHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Logo(
            modifier = Modifier.size(48.dp),
            tint = Colors.White
        )
        VerticalSpacer(24.dp)
        Text(
            text = stringResource(Res.string.welcome_authentication_welcome_title) + " " + stringResource(
                Res.string.app_name
            ),
            style = TextStyles.headingMdBold(),
            color = Colors.White
        )
    }
}
