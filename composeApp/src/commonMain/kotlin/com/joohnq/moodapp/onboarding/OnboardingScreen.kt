@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class OnboardingScreen : Screen {
    @Composable
    override fun Content() {
        OnboardingMoodRateScreen()
    }
}
