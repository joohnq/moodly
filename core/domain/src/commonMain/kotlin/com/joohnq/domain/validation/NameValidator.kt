package com.joohnq.domain.validation

class NameValidator : Validator<String> {
    override fun validate(input: String): Boolean {
        if (input.trim().isEmpty())
            throw Exception("Name cannot be empty")

        return true
    }
}
