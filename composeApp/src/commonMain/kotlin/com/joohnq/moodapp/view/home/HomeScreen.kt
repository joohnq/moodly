package com.joohnq.moodapp.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.BasicScreen
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

class HomeScreen : BasicScreen() {
    @Composable
    override fun Init() {
        val moodsViewModel: MoodsViewModel = koinViewModel()

        SideEffect { moodsViewModel.getMoods() }

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