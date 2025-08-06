package com.joohnq.onboarding.impl.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.joohnq.api.use_case.UpdateMedicationsSupplementsUseCase
import com.joohnq.api.use_case.UpdatePhysicalSymptomsUseCase
import com.joohnq.api.use_case.UpdateSoughtHelpUseCase
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toDomain
import com.joohnq.mood.api.use_case.AddMoodUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.sleep_quality.api.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toDomain
import com.joohnq.stress_level.api.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toDomain
import com.joohnq.ui.BaseViewModel
import com.joohnq.user.impl.ui.mapper.MedicationsSupplementsResourceMapper.toDomain
import com.joohnq.user.impl.ui.mapper.PhysicalSymptomsResourceMapper.toDomain
import com.joohnq.user.impl.ui.mapper.ProfessionalHelpResourceMapper.toDomain
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val addMoodUseCase: AddMoodUseCase,
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val updatePhysicalSymptomsUseCase: UpdatePhysicalSymptomsUseCase,
    private val updateSoughtHelpUseCase: UpdateSoughtHelpUseCase,
    private val updateMedicationsSupplementsUseCase: UpdateMedicationsSupplementsUseCase,
    private val updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase,
    initialState: OnboardingContract.State = OnboardingContract.State(),
) : BaseViewModel<OnboardingContract.State, OnboardingContract.Intent, OnboardingContract.SideEffect>(
        initialState = initialState
    ),
    OnboardingContract.ViewModel {
    override fun onIntent(intent: OnboardingContract.Intent) {
        when (intent) {
            is OnboardingContract.Intent.ChangeMood ->
                updateState { it.copy(moodRecord = it.moodRecord.copy(mood = intent.mood)) }

            is OnboardingContract.Intent.ChangeSleepQuality ->
                updateState { it.copy(sleepQuality = it.sleepQuality.copy(sleepQuality = intent.sleepQuality)) }

            is OnboardingContract.Intent.ChangeMoodDescription ->
                updateState { it.copy(moodRecord = it.moodRecord.copy(description = intent.description)) }

            is OnboardingContract.Intent.ChangeStressLevel ->
                updateState { it.copy(stressLevel = it.stressLevel.copy(stressLevel = intent.stressLevel)) }

            is OnboardingContract.Intent.ChangeMedicationsSupplements ->
                updateState { it.copy(medicationsSupplements = intent.medicationsSupplements) }

            is OnboardingContract.Intent.ChangePhysicalSymptoms ->
                updateState { it.copy(physicalSymptoms = intent.physicalSymptoms) }

            is OnboardingContract.Intent.ChangeProfessionalHelp ->
                updateState { it.copy(soughtHelp = intent.professionalHelp) }

            is OnboardingContract.Intent.ChangeSliderValue ->
                updateState { it.copy(sliderValue = intent.sliderValue) }

            OnboardingContract.Intent.AddItems -> addItems()
        }
    }

    private fun addItems() {
        viewModelScope.launch {
            val state = state.value

            if (state.physicalSymptoms == null || state.soughtHelp == null || state.medicationsSupplements == null) {
                error("Missing fields")
            }

            try {
                listOf(
                    async { addMoodUseCase(state.moodRecord.toDomain()).getOrThrow() },
                    async { addSleepQualityUseCase(state.sleepQuality.toDomain()).getOrThrow() },
                    async { addStressLevelUseCase(state.stressLevel.toDomain()).getOrThrow() },
                    async { updatePhysicalSymptomsUseCase(state.physicalSymptoms.toDomain()).getOrThrow() },
                    async { updateSoughtHelpUseCase(state.soughtHelp.toDomain()).getOrThrow() },
                    async { updateMedicationsSupplementsUseCase(state.medicationsSupplements.toDomain()).getOrThrow() }
                ).awaitAll()

                updateSkipOnboardingUseCase(true).getOrThrow()
                emitEffect(OnboardingContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(OnboardingContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
