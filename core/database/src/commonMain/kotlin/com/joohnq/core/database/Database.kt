package com.joohnq.core.database

abstract class Database<T> {
    abstract operator fun invoke(): T
}