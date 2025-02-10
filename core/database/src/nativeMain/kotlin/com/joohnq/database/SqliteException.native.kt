package com.joohnq.database

import co.touchlab.sqliter.interop.SQLiteExceptionErrorCode
import co.touchlab.sqliter.interop.SqliteErrorType

actual val sqliteExceptionMapper: SqliteExceptionMapper = NativeSqliteExceptionMapper()

private class NativeSqliteExceptionMapper : SqliteExceptionMapper {
    @Suppress("CyclomaticComplexMethod")
    override fun map(t: Throwable): SqliteException {
        val opResult = when {
            t is SQLiteExceptionErrorCode -> when (t.errorType) {
                SqliteErrorType.SQLITE_OK -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_ERROR -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_INTERNAL -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_PERM -> SqliteOperationResult.PERM
                SqliteErrorType.SQLITE_ABORT -> SqliteOperationResult.ABORT
                SqliteErrorType.SQLITE_BUSY -> SqliteOperationResult.BUSY
                SqliteErrorType.SQLITE_LOCKED -> SqliteOperationResult.LOCKED
                SqliteErrorType.SQLITE_NOMEM -> SqliteOperationResult.OUT_MEMORY
                SqliteErrorType.SQLITE_READONLY -> SqliteOperationResult.READONLY
                SqliteErrorType.SQLITE_INTERRUPT -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_IOERR -> SqliteOperationResult.DISK
                SqliteErrorType.SQLITE_CORRUPT -> SqliteOperationResult.CORRUPT
                SqliteErrorType.SQLITE_NOTFOUND -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_FULL -> SqliteOperationResult.FULL
                SqliteErrorType.SQLITE_CANTOPEN -> SqliteOperationResult.CANT_OPEN
                SqliteErrorType.SQLITE_PROTOCOL -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_EMPTY -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_SCHEMA -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_TOOBIG -> SqliteOperationResult.TOO_BIG
                SqliteErrorType.SQLITE_CONSTRAINT -> SqliteOperationResult.CONSTRAINT
                SqliteErrorType.SQLITE_MISMATCH -> SqliteOperationResult.MISMATCH
                SqliteErrorType.SQLITE_MISUSE -> SqliteOperationResult.MISUSE
                SqliteErrorType.SQLITE_NOLFS -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_AUTH -> SqliteOperationResult.PERM // Almost...
                SqliteErrorType.SQLITE_FORMAT -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_RANGE -> SqliteOperationResult.RANGE
                SqliteErrorType.SQLITE_NOTADB -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_NOTICE -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_WARNING -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_ROW -> SqliteOperationResult.UNKNOWN
                SqliteErrorType.SQLITE_DONE -> SqliteOperationResult.DONE
            }

            else -> SqliteOperationResult.UNKNOWN
        }
        return SqliteException(opResult, t)
    }
}