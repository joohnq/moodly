package com.joohnq.database

import android.database.sqlite.*

actual val sqliteExceptionMapper: SqliteExceptionMapper = AndroidSqliteExceptionMapper()

private class AndroidSqliteExceptionMapper : SqliteExceptionMapper {
    override fun map(t: Throwable): SqliteException {
        val opResult = when (t) {
            is SQLiteAbortException -> SqliteOperationResult.ABORT
            is SQLiteAccessPermException -> SqliteOperationResult.PERM
            is SQLiteBindOrColumnIndexOutOfRangeException -> SqliteOperationResult.RANGE
            is SQLiteBlobTooBigException -> SqliteOperationResult.TOO_BIG
            is SQLiteCantOpenDatabaseException -> SqliteOperationResult.CANT_OPEN
            is SQLiteConstraintException -> SqliteOperationResult.CONSTRAINT
            is SQLiteDatabaseCorruptException -> SqliteOperationResult.CORRUPT
            is SQLiteDatabaseLockedException -> SqliteOperationResult.BUSY
            is SQLiteDatatypeMismatchException -> SqliteOperationResult.MISMATCH
            is SQLiteDiskIOException -> SqliteOperationResult.DISK
            is SQLiteDoneException -> SqliteOperationResult.DONE
            is SQLiteFullException -> SqliteOperationResult.FULL
            is SQLiteMisuseException -> SqliteOperationResult.MISUSE
            is SQLiteOutOfMemoryException -> SqliteOperationResult.OUT_MEMORY
            is SQLiteReadOnlyDatabaseException -> SqliteOperationResult.READONLY
            is SQLiteTableLockedException -> SqliteOperationResult.LOCKED
            else -> SqliteOperationResult.UNKNOWN
        }
        return SqliteException(opResult, t)
    }
}