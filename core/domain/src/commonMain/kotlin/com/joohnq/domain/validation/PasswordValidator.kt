package com.joohnq.domain.validation

class PasswordValidator() : Validator<String> {
    override fun validate(input: String): Boolean {
        if (input.trim().isEmpty())
            throw Exception("Password cannot be empty")

        if (input.length < 6)
            throw Exception("Password must be at least 6 characters")

        return true
    }
}