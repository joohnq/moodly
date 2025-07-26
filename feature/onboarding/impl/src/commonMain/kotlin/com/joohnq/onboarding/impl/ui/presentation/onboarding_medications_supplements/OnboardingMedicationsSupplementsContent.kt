package com.joohnq.onboarding.impl.ui.presentation.onboarding_medications_supplements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.impl.ui.component.MedicationsSupplementsRadioButton
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.medications_supplements_title
import com.joohnq.user.impl.ui.mapper.getAllMedicationsSupplementsResource
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingMedicationsSupplementsContent(
    state: MedicationsSupplementsResource?,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingContract.Intent) -> Unit = {},
) {
    val options: List<MedicationsSupplementsResource> =
        remember { getAllMedicationsSupplementsResource() }

    _root_ide_package_.com.joohnq.onboarding.impl.ui.presentation.OnboardingBaseComponent(
        page = 5,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = state != null,
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().aspectRatio(1f),
            userScrollEnabled = false,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(options) { option: MedicationsSupplementsResource ->
                MedicationsSupplementsRadioButton(
                    modifier = Modifier.fillMaxSize().aspectRatio(1f),
                    text = stringResource(option.text),
                    icon = option.icon,
                    selected = state == option,
                    onClick = {
                        onAction(
                            OnboardingContract.Intent.UpdateUserMedicationsSupplements(option)
                        )
                    }
                )
            }
        }
    }
}