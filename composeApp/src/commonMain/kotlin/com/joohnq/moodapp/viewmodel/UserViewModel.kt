package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.User
import com.joohnq.moodapp.model.flowGetTheOne
import com.joohnq.moodapp.view.entities.MedicationsSupplements
import com.joohnq.moodapp.view.entities.PhysicalSymptoms
import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserViewModel(
    private val realm: Realm,
    private val dispatcherIo: CoroutineDispatcher
) :
    ScreenModel {

    fun setUserSoughtHelp(soughtHelp: Boolean) = screenModelScope.launch(dispatcherIo) {
        try {
            val user = realm.flowGetTheOne<User>()
                ?: throw Exception("No user found")
            realm.write {
                findLatest(user)?.let { usePrefs ->
                    usePrefs.soughtHelp = soughtHelp
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setUserPhysicalPain(physicalSymptoms: PhysicalSymptoms) =
        screenModelScope.launch(dispatcherIo) {
            try {
                val user = realm.flowGetTheOne<User>()
                    ?: throw Exception("No user found")
                realm.write {
                    findLatest(user)?.let { usePrefs ->
                        usePrefs.physicalSymptoms = physicalSymptoms
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    fun setUserMedicationsSupplements(medicationsSupplements: MedicationsSupplements) =
        screenModelScope.launch(dispatcherIo) {
            try {
                val user = realm.flowGetTheOne<User>()
                    ?: throw Exception("No user found")
                realm.write {
                    findLatest(user)?.let { usePrefs ->
                        usePrefs.medicationsSupplements = medicationsSupplements
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
}