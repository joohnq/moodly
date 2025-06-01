package com.joohnq.domain.validation

interface Validator<T> {
    fun validate(input: T): Boolean
}
