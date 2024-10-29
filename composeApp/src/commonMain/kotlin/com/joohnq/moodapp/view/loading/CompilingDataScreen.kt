package com.joohnq.moodapp.view.loading

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
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToHomeScreen
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CompilingData(
    navigation: NavController,
    moodsViewModel: MoodsViewModel = koinNavViewModel(),
    userViewModel: UserViewModel = koinNavViewModel()
) {
    val monthlyMoods by moodsViewModel.monthlyMoods.collectAsState()
    val user by userViewModel.user.collectAsState()

    SideEffect {
        moodsViewModel.getMonthlyMoods()
        userViewModel.getUser()
    }

    LaunchedEffect(monthlyMoods, user) {
        when (monthlyMoods is UiState.Success && user is UiState.Success) {
            true -> navigation.onNavigateToHomeScreen()
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
