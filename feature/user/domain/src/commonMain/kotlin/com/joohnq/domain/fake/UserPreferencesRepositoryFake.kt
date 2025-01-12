package com.joohnq.domain.fake

import com.joohnq.core.test.CustomFake
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository

class UserPreferencesRepositoryFake : UserPreferencesRepository, CustomFake {
    override var shouldThrowError: Boolean = false
    private var item: UserPreferences? = null

    override suspend fun getUserPreferences(): Result<UserPreferences> {
        if (shouldThrowError) return Result.failure(Exception("Failed to get user preferences"))

        return item?.let { Result.success(it) }
            ?: Result.failure(Exception("User preferences not found"))
    }

    override suspend fun addUserPreferences(userPreferences: UserPreferences): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to add user preferences"))

        item = userPreferences

        return Result.success(true)
    }

    override suspend fun insertUserPreferences(): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to insert user preferences"))

        item = UserPreferences()

        return Result.success(true)
    }

    override suspend fun updateSkipWelcome(value: Boolean): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user skip welcome"))

        item = item?.copy(skipWelcome = value)

        return Result.success(true)
    }

    override suspend fun updateSkipOnboarding(value: Boolean): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user skip onboarding"))

        item = item?.copy(skipOnboarding = value)

        return Result.success(true)
    }

    override suspend fun updateSkipAuth(value: Boolean): Result<Boolean> {
        if (shouldThrowError) return Result.failure(Exception("Failed to update user skip auth"))

        item = item?.copy(skipAuth = value)

        return Result.success(true)
    }
}