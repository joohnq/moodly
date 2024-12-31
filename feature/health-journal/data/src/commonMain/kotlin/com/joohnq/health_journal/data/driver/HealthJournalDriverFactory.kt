package com.joohnq.health_journal.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class HealthJournalDriverFactory {
    fun createDriver(): SqlDriver
}