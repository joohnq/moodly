package com.joohnq.core.test

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

actual fun createTestDriver(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return AndroidSqliteDriver(schema, app, null)
}

@RunWith(RobolectricTestRunner::class)
actual abstract class RobolectricTests