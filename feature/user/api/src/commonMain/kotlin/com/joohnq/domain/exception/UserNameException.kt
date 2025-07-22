package com.joohnq.domain.exception

sealed class UserNameException(override val message: String) : Exception(message) {
    data object EmptyName : UserNameException("Name can't be empty")
}