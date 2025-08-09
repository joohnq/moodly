package com.joohnq.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.joohnq.gratefulness.api.entity.dao.GratefulnessDao
import com.joohnq.gratefulness.api.entity.dto.GratefulnessDTO

@Database(entities = [GratefulnessDTO::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gratefulnessDao(): GratefulnessDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
