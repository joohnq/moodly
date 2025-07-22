package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.ui.components.MedicationsSupplementsRadioButton
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.viewmodel.OnboardingIntent
import com.joohnq.onboarding.ui.viewmodel.OnboardingState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.medications_supplements_title
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.user.impl.ui.mapper.getAllMedicationsSupplementsResource
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingMedicationsSupplementsUI(
    state: OnboardingState?,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingIntent) -> Unit = {},
) {
    val options: List<MedicationsSupplementsResource> =
        remember { getAllMedicationsSupplementsResource() }

    OnboardingBaseComponent(
        page = 5,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = state?.medicationsSupplements != null,
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) },
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
                    paddingValues = PaddingValues(all = 16.dp),
                    text = stringResource(option.text),
                    icon = option.icon.copy(modifier = Modifier.size(Dimens.Icon)),
                    selected = state?.medicationsSupplements == option,
                    colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                    shape = Dimens.Shape.Medium,
                    textStyle = TextStyles.TextMdBold(),
                    onClick = {
                        onAction(
                            OnboardingIntent.UpdateUserMedicationsSupplements(option)
                        )
                    }
                )
            }
        }
    }
}
