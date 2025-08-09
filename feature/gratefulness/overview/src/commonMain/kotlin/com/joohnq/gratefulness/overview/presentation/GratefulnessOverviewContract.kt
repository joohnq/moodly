package com.joohnq.gratefulness.overview.presentation

import com.joohnq.api.getNow
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.entity.Quote
import com.joohnq.gratefulness.api.mapper.GratefulnessMapper.getTodayItem
import com.joohnq.ui.UnidirectionalViewModel
import kotlinx.datetime.LocalDate

sealed interface GratefulnessOverviewContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class ChangeSelectedDate(
            val date: LocalDate,
        ) : Intent

        data class Delete(
            val id: Int,
        ) : Intent
    }

    sealed interface SideEffect {
        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val items: List<Gratefulness> = listOf(),
        val quote: Quote? = null,
        val isLoading: Boolean = false,
        val isError: String? = null,
        val selectedDate: LocalDate = getNow().date,
        val selectedGratefulness: Gratefulness? = null,
    ) {
        val insights: List<String>
            get() = items.map { item -> item.iAmGratefulFor }

        val todayItem: Gratefulness?
            get() = items.getTodayItem()
    }

    sealed interface Event {
        data object GoBack : Event

        data object NavigateToAddGratefulness : Event

        data object NavigateToGratefulnessHistory :
            Event
    }
}