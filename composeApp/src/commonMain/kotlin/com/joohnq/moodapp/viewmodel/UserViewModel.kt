package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.view.entities.MedicationsSupplements
import com.joohnq.moodapp.view.entities.PhysicalSymptoms
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDAO: UserDAO,
    private val dispatcherIo: CoroutineDispatcher
) :
    ScreenModel {

    fun setUserSoughtHelp(soughtHelp: Boolean) = screenModelScope.launch(dispatcherIo) {
        userDAO.setUserSoughtHelp(soughtHelp)
    }

    fun setUserPhysicalPain(physicalPain: PhysicalSymptoms) =
        screenModelScope.launch(dispatcherIo) {
            userDAO.setUserPhysicalPain(physicalPain)
        }

    fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        screenModelScope.launch(dispatcherIo) {
            userDAO.setUserMedicationsSupplements(medicationsSupplements)
        }
}