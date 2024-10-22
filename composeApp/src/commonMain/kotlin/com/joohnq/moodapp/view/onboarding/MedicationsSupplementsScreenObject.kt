package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.MedicationsSupplements
import com.joohnq.moodapp.view.components.MedicationsSupplementsRadioButton
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.medications_supplements_title
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object MedicationsSupplementsScreenObject

@Composable
fun MedicationsSupplementsScreen(
    onGoBack: () -> Unit,
    onNavigateToStressRate: () -> Unit
) {
    var isContinueButtonVisible by remember { mutableStateOf(false) }
    val userViewModel: UserViewModel = koinViewModel()
    var selectedOption by rememberSaveable(stateSaver = MedicationsSupplements.getSaver()) {
        mutableStateOf(null)
    }
    val options: List<MedicationsSupplements> = remember { MedicationsSupplements.getAll() }
    val scope = rememberCoroutineScope()
    val ioDispatcher: CoroutineDispatcher = koinInject()

    LaunchedEffect(selectedOption) {
        isContinueButtonVisible =
            selectedOption != null
    }

    OnboardingBaseComponent(
        page = 5,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = isContinueButtonVisible,
        onBack = onGoBack,
        onContinue = { onSomethingWentWrong ->
            scope.launch(ioDispatcher) {
                val res = userViewModel.setUserMedicationsSupplements(
                    selectedOption ?: MedicationsSupplements.PreferNotToSay
                )
                if (!res) {
                    onSomethingWentWrong()
                    return@launch
                }

                withContext(Dispatchers.Main) {
                    onNavigateToStressRate()
                }
            }
        },
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().aspectRatio(1f),
            userScrollEnabled = false,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(options) { option: MedicationsSupplements ->
                MedicationsSupplementsRadioButton(
                    option = option,
                    selected = selectedOption == option,
                ) { selectedOption = option }
            }
        }
    }
}
