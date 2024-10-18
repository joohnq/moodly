package com.joohnq.moodapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.model.converters.UserConverter
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.USER_DATABASE)
@Serializable
data class User(
    @PrimaryKey val id: String = "1",
    val name: String,
    @ColumnInfo(name = DatabaseConstants.MEDICATIONS_SUPPLEMENTS) val medicationsSupplements: MedicationsSupplements,
    @ColumnInfo(name = DatabaseConstants.SOUGHT_HELP) val soughtHelp: Boolean,
    @ColumnInfo(name = DatabaseConstants.PHYSICAL_SYMPTOMS) val physicalSymptoms: PhysicalSymptoms
)
