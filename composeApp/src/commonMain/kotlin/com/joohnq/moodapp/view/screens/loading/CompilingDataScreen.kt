package com.joohnq.moodapp.view.screens.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel

@Composable
fun CompilingDataScreen(
    navigation: NavController,
    statsViewModel: StatsViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
) {
    val moodsState by statsViewModel.statsState.collectAsState()
    val userState by userViewModel.userState.collectAsState()
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()

    SideEffect {
        statsViewModel.getStats()
        userViewModel.getUser()
        stressLevelViewModel.getStressLevelRecords()
        sleepQualityViewModel.getSleepQualityRecords()
    }

    LaunchedEffect(
        moodsState,
        userState,
        stressLevelState,
        sleepQualityState
    ) {
        when (moodsState.statsRecords is UiState.Success && stressLevelState.items is UiState.Success && sleepQualityState.items is UiState.Success && userState.user is UiState.Success) {
            true -> navigation.onNavigateToHomeGraph()
            else -> Unit
        }
    }

    Scaffold(
        containerColor = Colors.Orange40,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column {
                Text(
                    "Compiling Data",
                    textAlign = TextAlign.Center,
                    style = TextStyles.CompilingDataTitle()
                )
            }
        }
    }
}
