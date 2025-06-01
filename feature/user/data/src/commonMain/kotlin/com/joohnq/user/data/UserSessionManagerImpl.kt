package com.joohnq.user.data

import com.joohnq.auth.domain.repository.AuthRepository
import com.joohnq.domain.UserSessionManager
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserSessionState
import com.joohnq.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@OptIn(ExperimentalCoroutinesApi::class)
class UserSessionManagerImpl(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : UserSessionManager {
    override val session: Flow<UiState<UserSessionState>> =
        authRepository.observeAuthState()
            .flatMapLatest { authUser ->
                if (authUser == null) {
                    flow { emit(UiState.Idle) }
                } else {
                    userRepository.getUser(authUser.id)
                        .map<User, UiState<UserSessionState>> { user ->
                            UiState.Success(UserSessionState(authUser.id, user))
                        }
                        .onStart { emit(UiState.Loading) }
                        .catch { e -> emit(UiState.Error(e)) }
                }
            }
            .distinctUntilChanged()

    override suspend fun clearSession() {
        authRepository.signOut()
    }
}