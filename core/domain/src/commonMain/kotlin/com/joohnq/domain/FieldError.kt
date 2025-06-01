package com.joohnq.domain

sealed class FieldError(open val error: Throwable): Exception(error.message, error){
    data class NameError(override val error: Throwable): FieldError(error)
    data class EmailError(override val error: Throwable): FieldError(error)
    data class PasswordError(override val error: Throwable): FieldError(error)
}