package com.joohnq.test

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

expect fun createTestDriver(schema: SqlSchema<QueryResult.Value<Unit>>): SqlDriver

expect abstract class RobolectricTests()