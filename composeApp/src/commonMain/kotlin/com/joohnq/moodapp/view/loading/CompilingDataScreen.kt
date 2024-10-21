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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import kotlinx.serialization.Serializable

@Serializable
object CompilingDataScreen

@Composable
fun CompilingData() {
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
