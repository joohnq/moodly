package com.joohnq.domain.validation

class EmailValidator() : Validator<String> {
    override fun validate(input: String): Boolean {
        if (input.trim().isEmpty())
            throw Exception("Email cannot be empty")

        if (!input.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$".toRegex()))
            throw Exception("Invalid email format")

        return true
    }
}