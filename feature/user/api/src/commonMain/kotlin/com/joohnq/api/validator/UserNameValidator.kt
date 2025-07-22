package com.joohnq.api.validator

import com.joohnq.api.exception.UserNameException

object UserNameValidator {
    operator fun invoke(name: String): Boolean {
        if (name.trim().isEmpty()) throw UserNameException.EmptyName
        return true
    }
}