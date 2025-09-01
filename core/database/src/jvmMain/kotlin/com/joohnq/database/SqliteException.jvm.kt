package com.joohnq.database

actual val sqliteExceptionMapper: SqliteExceptionMapper = DesktopSqliteExceptionMapper()

private class DesktopSqliteExceptionMapper : SqliteExceptionMapper {
    override fun map(t: Throwable): SqliteException {
        val opResult = SqliteOperationResult.UNKNOWN
        return SqliteException(opResult, t)
    }
}
