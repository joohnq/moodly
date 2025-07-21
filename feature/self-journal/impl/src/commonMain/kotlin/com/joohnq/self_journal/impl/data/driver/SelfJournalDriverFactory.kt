package com.joohnq.self_journal.impl.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class SelfJournalDriverFactory {
    fun createDriver(): SqlDriver
}