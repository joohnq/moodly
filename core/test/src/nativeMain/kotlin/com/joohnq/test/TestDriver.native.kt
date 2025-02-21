package com.joohnq.test

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import app.cash.sqldelight.driver.native.wrapConnection
import co.touchlab.sqliter.DatabaseConfiguration

actual fun createTestDriver(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver {
    return NativeSqliteDriver(
        DatabaseConfiguration(
            name = "library.db",
            version = schema.version.toInt(),
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            inMemory = true
        )
    )
}

actual abstract class RobolectricTests