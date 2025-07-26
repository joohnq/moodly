package com.joohnq.database

abstract class Database<T> {
    abstract operator fun invoke(): T
}
