package com.joohnq.shared_resources.components.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.empty
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyView() {
    Column(
        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Drawables.Images.NotFound),
            contentDescription = stringResource(Res.string.empty),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = stringResource(Res.string.empty),
            style = TextStyles.Text2xlExtraBold(),
            color = Colors.Brown80
        )
    }
}
