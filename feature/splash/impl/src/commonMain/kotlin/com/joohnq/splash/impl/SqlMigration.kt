package com.joohnq.splash.impl

import com.joohnq.api.mapper.ImageTypeMapper.toImageType
import com.joohnq.api.mapper.MedicationsSupplementsMapper.toMedicationsSupplements
import com.joohnq.api.mapper.PhysicalSymptomsMapper.toPhysicalSymptoms
import com.joohnq.api.mapper.ProfessionalHelpMapper.toProfessionalHelp
import com.joohnq.api.mapper.StringMapper.toTime
import com.joohnq.api.mapper.TimeMapper.toFormattedTimeString
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.mapper.LocalDateMapper.toLocalDate
import com.joohnq.database.mapper.LocalDateTimeMapper.toLocalDateTime
import com.joohnq.gratefulness.api.entity.dao.GratefulnessDao
import com.joohnq.mood.add.data.database.MoodDatabase
import com.joohnq.mood.api.mapper.MoodMapper.toMood
import com.joohnq.mood.database.MoodDatabaseSql
import com.joohnq.mood.database.MoodRecord
import com.joohnq.preferences.api.repository.PreferencesRepository
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import com.joohnq.self_journal.impl.data.database.SelfJournalDatabase
import com.joohnq.selfjournal.database.SelfJournalRecord
import com.joohnq.sleep_quality.api.mapper.SleepInfluencesMapper.mapToIds
import com.joohnq.sleep_quality.api.mapper.SleepInfluencesMapper.toInfluence
import com.joohnq.sleep_quality.api.mapper.SleepQualityMapper.toSleepQuality
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.impl.data.database.SleepQualityDatabase
import com.joohnq.sleepquality.database.SleepQualityRecord
import com.joohnq.storage.api.FileStorage
import com.joohnq.stress_level.api.mapper.StressLevelMapper.toStressLevel
import com.joohnq.stress_level.api.mapper.StressorMapper.join
import com.joohnq.stress_level.api.mapper.StressorMapper.toStressor
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.impl.data.database.StressLevelDatabase
import com.joohnq.stresslevel.database.StressLevelRecord
import com.joohnq.user.database.User
import com.joohnq.user.database.UserDatabaseSql
import com.joohnq.user.impl.data.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class SqlMigration(
    private val moodSql: MoodDatabaseSql,
    private val selfJournalSql: SelfJournalDatabaseSql,
    private val sleepQualitySql: SleepQualityDatabaseSql,
    private val stressLevelSql: StressLevelDatabaseSql,
    private val userSql: UserDatabaseSql,
    private val gratefulnessDao: GratefulnessDao,
    private val appSql: AppDatabaseSql,
    private val preferencesRepository: PreferencesRepository,
    private val moodDatabase: MoodDatabase,
    private val selfJournalDatabase: SelfJournalDatabase,
    private val sleepQualityDatabase: SleepQualityDatabase,
    private val stressLevelDatabase: StressLevelDatabase,
    private val userDatabase: UserDatabase,
    private val fileStorage: FileStorage,
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val moods =
                moodSql.moodRecordQueries
                    .getMoodRecords()
                    .executeAsList()
                    .map { it.toMain() }
            val selfJournals =
                selfJournalSql.selfJournalRecordQueries
                    .getSelfJournalRecords()
                    .executeAsList()
                    .map { it.toMain() }
            val sleepQualities =
                sleepQualitySql.sleepQualityRecordQueries
                    .getSleepQualities()
                    .executeAsList()
                    .map { it.toMain() }
            val stressLevels =
                stressLevelSql.stressLevelRecordQueries.getStressLevels().executeAsList().map {
                    it.toMain()
                }
            val gratefulnesses = gratefulnessDao.getAll()
            val user =
                userSql.userQueries
                    .getUser()
                    .executeAsOneOrNull()
                    ?.toMain()
                    ?: com.joohnq.api.entity
                        .User()

            appSql.transaction {
                moods.forEach {
                    appSql.moodsQueries.addWithIdAndCreatedAt(
                        id = it.id,
                        level = it.mood.id,
                        description = it.description,
                        createdAt = it.createdAt.toString()
                    )
                }
                selfJournals.forEach {
                    appSql.selfJournalsQueries.addWithIdAndCreatedAt(
                        id = it.id,
                        mood = it.mood.id,
                        title = it.title,
                        description = it.description,
                        createdAt = it.createdAt.toString()
                    )
                }
                sleepQualities.forEach {
                    appSql.sleepQualitiesQueries.addWithIdAndCreatedAt(
                        id = it.id,
                        quality = it.quality.id,
                        start = it.start.toFormattedTimeString(),
                        end = it.end.toFormattedTimeString(),
                        influences = it.influences.mapToIds(),
                        createdAt = it.createdAt.toString()
                    )
                }
                stressLevels.forEach {
                    appSql.stressLevelsQueries.addWithIdAndCreatedAt(
                        id = it.id,
                        level = it.level.id,
                        stressors = it.stressors.join(),
                        createdAt = it.createdAt.toString()
                    )
                }
                gratefulnesses.forEach {
                    appSql.gratefulnessesQueries.addWithIdAndCreatedAt(
                        id = it.id,
                        iAmGratefulFor = it.iAmGratefulFor,
                        smallThingIAppreciate = it.smallThingIAppreciate,
                        description = it.description,
                        createdAt = it.createdAt.toString()
                    )
                }
                appSql.usersQueries.update(
                    name = user.name,
                    image = user.image,
                    imageType = user.imageType.name,
                    medicationsSupplements = user.medicationsSupplements.id,
                    soughtProfessionalHelp = user.soughtHelp.id,
                    physicalSymptoms = user.physicalSymptoms.id,
                    createdAt = user.createdAt.toString()
                )
            }

            moodDatabase.drop()
            selfJournalDatabase.drop()
            sleepQualityDatabase.drop()
            stressLevelDatabase.drop()
            userDatabase.drop()

            fileStorage.deleteDatabase("user.db")
            fileStorage.deleteDatabase("self_journal")
            fileStorage.deleteDatabase("stress_level.db")
            fileStorage.deleteDatabase("user.db")
            fileStorage.deleteDatabase("sleep_quality.db")
            fileStorage.deleteDatabase("mood.db")
            fileStorage.deleteDatabase("app.db")

            preferencesRepository.updateSkipSqlMigration(true)
        }
    }
}

private fun MoodRecord.toMain(): com.joohnq.mood.api.entity.MoodRecord =
    com.joohnq.mood.api.entity.MoodRecord(
        id = id,
        mood = mood.toMood(),
        description = description,
        createdAt = createdAt.toLocalDateTime()
    )

private fun SelfJournalRecord.toMain(): com.joohnq.self_journal.api.entity.SelfJournalRecord =
    com.joohnq.self_journal.api.entity.SelfJournalRecord(
        id = id,
        mood = mood.toMood(),
        title = title,
        description = description,
        createdAt = createdAt.toLocalDateTime()
    )

private fun SleepQualityRecord.toMain(): com.joohnq.sleep_quality.api.entity.SleepQualityRecord =
    com.joohnq.sleep_quality.api.entity.SleepQualityRecord(
        id = id,
        quality = sleepQuality.toSleepQuality(),
        start = startSleeping.toTime(),
        end = endSleeping.toTime(),
        influences =
            Json.decodeFromString<List<Long>>(sleepInfluences).map {
                it.toInfluence()
            },
        createdAt = createdAt.toLocalDate()
    )

private fun StressLevelRecord.toMain(): com.joohnq.stress_level.api.entity.StressLevelRecord =
    com.joohnq.stress_level.api.entity.StressLevelRecord(
        id = id,
        level = stressLevel.toStressLevel(),
        stressors =
            Json.decodeFromString<List<Long>>(stressors).map {
                it.toStressor()
            },
        createdAt = createdAt.toLocalDateTime()
    )

private fun User.toMain(): com.joohnq.api.entity.User =
    com.joohnq.api.entity.User(
        id = id,
        name = name,
        image = image,
        imageType = imageType.toImageType(),
        medicationsSupplements = medicationsSupplements.toMedicationsSupplements(),
        soughtHelp = soughtHelp.toProfessionalHelp(),
        physicalSymptoms = physicalSymptoms.toPhysicalSymptoms(),
        createdAt = dateCreated.toLocalDate()
    )
