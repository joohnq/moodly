package com.joohnq.domain.validator

import com.joohnq.domain.exception.UserNameException

object UserNameValidator {
    operator fun invoke(name: String): Boolean {
        if (name.trim().isEmpty()) throw UserNameException.EmptyName
        return true
    }
}