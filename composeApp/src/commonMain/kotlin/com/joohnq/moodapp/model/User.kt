package com.joohnq.moodapp.model

import com.joohnq.moodapp.view.entities.MedicationsSupplements
import com.joohnq.moodapp.view.entities.PhysicalSymptoms
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class User : RealmObject {
    @PrimaryKey
    var id: String = "1"
    var name: String? = null
    var medicationsSupplements: MedicationsSupplements? = null
    var soughtHelp: Boolean? = null
    var physicalSymptoms: PhysicalSymptoms? = null
}