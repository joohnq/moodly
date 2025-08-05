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
import kotlinx.coroutines.coroutineScope
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
            is OnboardingContract.Intent.UpdateMood ->
                updateState { it.copy(moodRecord = it.moodRecord.copy(mood = intent.mood)) }

            is OnboardingContract.Intent.UpdateSleepQuality ->
                updateState { it.copy(sleepQuality = it.sleepQuality.copy(sleepQuality = intent.sleepQuality)) }

            is OnboardingContract.Intent.UpdateMoodRecordDescription ->
                updateState { it.copy(moodRecord = it.moodRecord.copy(description = intent.description)) }

            is OnboardingContract.Intent.UpdateStressLevel ->
                updateState { it.copy(stressLevel = it.stressLevel.copy(stressLevel = intent.stressLevel)) }

            is OnboardingContract.Intent.UpdateUserMedicationsSupplements ->
                updateState { it.copy(medicationsSupplements = intent.medicationsSupplements) }

            is OnboardingContract.Intent.UpdateUserPhysicalSymptoms ->
                updateState { it.copy(physicalSymptoms = intent.physicalSymptoms) }

            is OnboardingContract.Intent.UpdateUserSoughtHelp ->
                updateState { it.copy(soughtHelp = intent.soughtHelp) }

            is OnboardingContract.Intent.UpdateSliderValue ->
                updateState { it.copy(sliderValue = intent.sliderValue) }

            OnboardingContract.Intent.AddItems -> addItems()
        }
    }

    private fun addItems() {
        viewModelScope.launch {
            val (
                physicalSymptoms,
                soughtHelp,
                medicationsSupplements,
                moodRecord,
                sleepQuality,
                stressLevel,
                _,
            ) = state.value

            if (physicalSymptoms == null || soughtHelp == null || medicationsSupplements == null) {
                emitEffect(OnboardingContract.SideEffect.ShowError("Missing fields"))
            }

            try {
                coroutineScope {
                    listOf(
                        async { addMoodUseCase(moodRecord.toDomain()) },
                        async { addSleepQualityUseCase(sleepQuality.toDomain()) },
                        async { addStressLevelUseCase(stressLevel.toDomain()) },
                        async { updatePhysicalSymptomsUseCase(physicalSymptoms!!.toDomain()) },
                        async { updateSoughtHelpUseCase(soughtHelp!!.toDomain()) },
                        async { updateMedicationsSupplementsUseCase(medicationsSupplements!!.toDomain()) }
                    ).awaitAll()
                }
                updatePreferences()
            } catch (e: Exception) {
                emitEffect(OnboardingContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }

    private fun updatePreferences() {
        viewModelScope.launch {
            coroutineScope {
                async { updateSkipOnboardingUseCase(true) }.await()
            }
            emitEffect(OnboardingContract.SideEffect.NavigateNext)
        }
    }
}
