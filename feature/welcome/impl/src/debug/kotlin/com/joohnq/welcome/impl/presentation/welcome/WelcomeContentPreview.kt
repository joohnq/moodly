package com.joohnq.welcome.impl.presentation.welcome

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import com.joohnq.welcome.impl.presentation.WelcomeParameterProvider
import com.joohnq.welcome.impl.ui.presentation.welcome.WelcomeContent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
private fun Preview(
    @PreviewParameter(WelcomeParameterProvider::class)
    item: Int,
) {
    WelcomeContent(
        pagerState = rememberPagerState(item) { 6 }
    )
}
