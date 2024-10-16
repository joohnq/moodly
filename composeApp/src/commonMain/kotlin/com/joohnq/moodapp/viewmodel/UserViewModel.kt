package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.view.entities.MedicationsSupplements
import com.joohnq.moodapp.view.entities.PhysicalSymptoms
import com.joohnq.moodapp.view.entities.User
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel(
    private val userDAO: UserDAO,
) : ScreenModel {
    private val _user: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Idle)
    val user: StateFlow<UiState<User>> = _user

    suspend fun setUserSoughtHelp(soughtHelp: Boolean) = try {
        userDAO.setUserSoughtHelp(soughtHelp)
        true
    } catch (e: Exception) {
        false
    }

    suspend fun setUserPhysicalPain(physicalPain: PhysicalSymptoms) =
        try {
            userDAO.setUserPhysicalPain(physicalPain)
            true
        } catch (e: Exception) {
            false
        }

    suspend fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Boolean =
        try {
            userDAO.setUserMedicationsSupplements(medicationsSupplements)
            true
        } catch (e: Exception) {
            false
        }

    suspend fun setUserName(name: String): Boolean = try {
        userDAO.setUserName(name)
        true
    } catch (e: Exception) {
        false
    }
}