package com.joohnq.freud_score.impl.ui.presentation.freud_score

import androidx.lifecycle.viewModelScope
import com.joohnq.freud_score.impl.ui.mapper.FreudScoreResourceMapper.toResource
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.calculateStatsFreudScore
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FreudScoreViewModel(
    private val getMoodsUseCase: GetMoodsUseCase,
    initialState: FreudScoreContract.State = FreudScoreContract.State(),
) : BaseViewModel<FreudScoreContract.State, FreudScoreContract.Intent, FreudScoreContract.SideEffect>(
        initialState = initialState
    ),
    FreudScoreContract.ViewModel {
    override fun onIntent(intent: FreudScoreContract.Intent) {}

    init {
        observe()
    }

    private fun observe() {
        viewModelScope.launch {
            getMoodsUseCase()
                .onEach { items ->
                    updateState {
                        it.copy(
                            freudScore = items.toResource().calculateStatsFreudScore().toResource()
                        )
                    }
                }.catch { e ->
                    emitEffect(FreudScoreContract.SideEffect.ShowError(e.message.toString()))
                }.launchIn(viewModelScope)
        }
    }
}
