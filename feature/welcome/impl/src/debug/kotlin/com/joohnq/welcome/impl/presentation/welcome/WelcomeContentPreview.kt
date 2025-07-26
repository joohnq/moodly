package com.joohnq.welcome.impl.presentation.welcome

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import com.joohnq.welcome.impl.ui.presentation.welcome.WelcomeContent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun WelcomeContentOnePreview() {
    WelcomeContent(
        pagerState = rememberPagerState(0) { 6 }
    )
}

@Preview
@Composable
fun WelcomeContentTwoPreview() {
    WelcomeContent(
        pagerState = rememberPagerState(1) { 6 }
    )
}

@Preview
@Composable
fun WelcomeContentThreePreview() {
    WelcomeContent(
        pagerState = rememberPagerState(2) { 6 }
    )
}

@Preview
@Composable
fun WelcomeContentFourPreview() {
    WelcomeContent(
        pagerState = rememberPagerState(3) { 6 }
    )
}

@Preview
@Composable
fun WelcomeContentFivePreview() {
    WelcomeContent(
        pagerState = rememberPagerState(4) { 6 }
    )
}

@Preview
@Composable
fun WelcomeContentSixPreview() {
    WelcomeContent(
        pagerState = rememberPagerState(5) { 6 }
    )
}

@Preview
@Composable
fun WelcomeContentSevenPreview() {
    WelcomeContent(
        pagerState = rememberPagerState(6) { 6 }
    )
}
