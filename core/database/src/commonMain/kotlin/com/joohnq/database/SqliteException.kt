package com.joohnq.database

interface SqliteExceptionMapper {
    fun map(t: Throwable): SqliteException
}

expect val sqliteExceptionMapper: SqliteExceptionMapper

enum class SqliteOperationResult {
    UNKNOWN,
    PERM,
    ABORT,
    BUSY,
    MISUSE,
    TOO_BIG,
    CANT_OPEN,
    CONSTRAINT,
    CORRUPT,
    RANGE,
    MISMATCH,
    DISK,
    DONE,
    FULL,
    OUT_MEMORY,
    READONLY,
    LOCKED,
}

class SqliteException(
    val opResult: SqliteOperationResult,
    cause: Throwable,
) : IllegalStateException(cause)
