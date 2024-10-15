package com.joohnq.moodapp.view.onb

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.utils.Log
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import org.koin.compose.koinInject

class OnbScreen : Screen {
    @Composable
    override fun Content() {
        val moodsViewModel: MoodsViewModel = koinInject()
        val moods by moodsViewModel.moods.collectAsState()

        SideEffect { moodsViewModel.getMoods() }

        LaunchedEffect(moods){
            Log(moods)
        }

        Scaffold(
            containerColor = Colors.Brown10,
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            Column(
                modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {}
        }
    }
}