package com.joohnq.onboarding.impl.viewmodel

import com.joohnq.ui.BaseViewModel

class OnboardingViewModel(
    initialState: OnboardingContract.State = OnboardingContract.State(),
) : BaseViewModel<OnboardingContract.State, OnboardingContract.Intent, OnboardingContract.SideEffect>(
    initialState = initialState
), OnboardingContract.ViewModel {
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
        }
    }
}