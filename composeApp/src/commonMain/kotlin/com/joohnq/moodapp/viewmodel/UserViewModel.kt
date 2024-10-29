package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.model.entities.MedicationsSupplements
import com.joohnq.moodapp.model.entities.PhysicalSymptoms
import com.joohnq.moodapp.model.entities.User
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDAO: UserDAO,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _user: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Idle)
    val user: StateFlow<UiState<User>> = _user

    fun getUser() = viewModelScope.launch {
        try {
            val user = userDAO.getUser()
            _user.value = UiState.Success(user)
        } catch (e: Exception) {
            _user.value = UiState.Error(e.message.toString())
        }
    }

    suspend fun iniUser() = executeWithBoolean(userDAO::initUser)

    /*
    * Insert in the database if the user already sought professional help
    * Tested
    * */
    suspend fun setUserSoughtHelp(soughtHelp: Boolean)  = executeWithBoolean{
       userDAO.setUserSoughtHelp(soughtHelp)
    }

    /*
    * Insert in the database if the user is experiencing physical pain
    * Tested
    * */
    suspend fun setUserPhysicalPain(physicalSymptoms: PhysicalSymptoms) = executeWithBoolean{
        userDAO.setUserPhysicalPain(physicalSymptoms)
    }

    /*
   * Insert in the database if the user is taking any medications or supplements
   * Tested
   * */
    suspend fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements) = executeWithBoolean{
        userDAO.setUserMedicationsSupplements(medicationsSupplements)
    }

    /*
  * Insert in the database the user name
  * Tested
  * */
    suspend fun setUserName(name: String) = executeWithBoolean {
        userDAO.setUserName(name)
    }
}