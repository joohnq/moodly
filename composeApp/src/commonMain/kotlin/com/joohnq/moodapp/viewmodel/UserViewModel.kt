package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.model.entities.MedicationsSupplements
import com.joohnq.moodapp.model.entities.PhysicalSymptoms
import com.joohnq.moodapp.model.entities.User
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(
    private val userDAO: UserDAO,
) : ScreenModel {
    private val _user: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Idle)
    val user: StateFlow<UiState<User>> = _user

    /*
    * Insert in the database if the user already sought professional help
    * Tested
    * */
    suspend fun setUserSoughtHelp(soughtHelp: Boolean) = executeWithBoolean {
        userDAO.setUserSoughtHelp(soughtHelp)
    }

    /*
    * Insert in the database if the user is experiencing physical pain
    * Tested
    * */
    suspend fun setUserPhysicalPain(physicalPain: PhysicalSymptoms) = executeWithBoolean {
        userDAO.setUserPhysicalPain(physicalPain)
    }

    /*
   * Insert in the database if the user is taking any medications or supplements
   * Tested
   * */
    suspend fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements): Boolean =
        executeWithBoolean {
            userDAO.setUserMedicationsSupplements(medicationsSupplements)
        }

    /*
  * Insert in the database the user name
  * Tested
  * */
    suspend fun setUserName(name: String): Boolean =
        executeWithBoolean { userDAO.setUserName(name) }
}