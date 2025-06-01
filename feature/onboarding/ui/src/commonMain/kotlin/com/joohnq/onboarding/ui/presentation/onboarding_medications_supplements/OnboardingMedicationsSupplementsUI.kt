package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.ui.components.MedicationsSupplementsRadioButton
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.medications_supplements_title
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.user.ui.resource.mapper.getAllMedicationsSupplementsResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingMedicationsSupplementsUI(
    state: OnboardingContract.State?,
    onEvent: (OnboardingContract.Event) -> Unit = {},
    onIntent: (OnboardingContract.Intent) -> Unit = {},
) {
    val options: List<MedicationsSupplementsResource> =
        remember { getAllMedicationsSupplementsResource() }

    OnboardingBaseComponent(
        page = 5,
        title = Res.string.medications_supplements_title,
        isContinueButtonVisible = state?.medicationsSupplements != null,
        onGoBack = { onEvent(OnboardingContract.Event.GoBack) },
        onContinue = { onEvent(OnboardingContract.Event.OnContinue) },
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
                    textStyle = TextStyles.textMdBold(),
                    onClick = {
                        onIntent(
                            OnboardingContract.Intent.UpdateUserMedicationsSupplements(option)
                        )
                    }
                )
            }
        }
    }
}
