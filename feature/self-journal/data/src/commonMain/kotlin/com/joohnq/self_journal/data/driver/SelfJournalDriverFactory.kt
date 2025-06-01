package com.joohnq.self_journal.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class SelfJournalDriverFactory {
    fun createDriver(): SqlDriver
}