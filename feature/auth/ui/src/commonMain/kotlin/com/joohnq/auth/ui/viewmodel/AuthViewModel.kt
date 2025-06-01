package com.joohnq.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.auth.domain.AuthException
import com.joohnq.auth.domain.entity.OAuthUser
import com.joohnq.auth.domain.use_case.GetAuthUserUseCase
import com.joohnq.auth.domain.use_case.SignInWithAppleUseCase
import com.joohnq.auth.domain.use_case.SignInWithEmailAndPasswordUseCase
import com.joohnq.auth.domain.use_case.SignInWithGoogleUseCase
import com.joohnq.auth.domain.use_case.SignOutUseCase
import com.joohnq.auth.domain.use_case.SignUpWithEmailAndPasswordUseCase
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserImage
import com.joohnq.domain.mapper.onStart
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getAuthUserUseCase: GetAuthUserUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithAppleUseCase: SignInWithAppleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val signUpWithEmailAndPasswordUseCase: SignUpWithEmailAndPasswordUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthContract.State())
    val state: StateFlow<AuthContract.State> = _state.asStateFlow()

    private val _signInState = MutableStateFlow(AuthContract.SignInState())
    val signInState: StateFlow<AuthContract.SignInState> = _signInState.asStateFlow()

    private val _signUpState = MutableStateFlow(AuthContract.SignUpState())
    val signUpState: StateFlow<AuthContract.SignUpState> = _signUpState.asStateFlow()

    private val _sideEffect = Channel<AuthContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onIntent(intent: AuthContract.Intent) {
        when (intent) {
            AuthContract.Intent.GetAuthUser -> getAuthUser()
            AuthContract.Intent.SignOut -> signOut()
            AuthContract.Intent.SignIn -> signIn()
            is AuthContract.Intent.SignInWithApple -> signInWithApple(intent.user)
            is AuthContract.Intent.SignInWithGoogle -> signInWithGoogle(intent.user)

            AuthContract.Intent.SignUp -> signUp()
            is AuthContract.Intent.SignInEmailChanged -> _signInState.update { it.copy(email = intent.email) }
            is AuthContract.Intent.SignInPasswordChanged -> _signInState.update { it.copy(password = intent.password) }
            is AuthContract.Intent.SignUpEmailChanged -> _signUpState.update { it.copy(email = intent.email) }
            is AuthContract.Intent.SignUpNameChanged -> _signUpState.update { it.copy(name = intent.name) }
            is AuthContract.Intent.SignUpPasswordChanged -> _signUpState.update { it.copy(password = intent.password) }
        }
    }

    private fun getAuthUser() = viewModelScope.launch {
        getAuthUserUseCase()
            .onStart {
                _state.update { it.copy(status = UiState.Loading) }
            }
            .catch { error ->
                _state.update { it.copy(status = UiState.Error(error)) }
            }
            .collect { user ->
                val authUser =
                    if (user?.id.isNullOrBlank() && user?.email.isNullOrBlank())
                        AuthUserState.NotAuthenticated
                    else AuthUserState.Authenticated(user)

                println(authUser)

                _state.update {
                    it.copy(
                        status = UiState.Success(authUser)
                    )
                }
            }
    }

    private fun signIn() = viewModelScope.launch {
        val (email, password, _) = _signInState.value

        _signInState.update { it.copy(status = UiState.Loading) }

        signInWithEmailAndPasswordUseCase(
            email = email,
            password = password,
        ).onFailure { error ->
            _signInState.update { it.copy(status = UiState.Error(error)) }
        }.onSuccess {
            _signInState.update { it.copy(status = UiState.Success(Unit)) }
        }
    }

    private fun signUp() = viewModelScope.launch {
        val (name, email, password, _) = _signUpState.value


        signUpWithEmailAndPasswordUseCase(
            name = name,
            email = email,
            password = password,
        )
            .onStart {
                _signInState.update { it.copy(status = UiState.Loading) }
            }.onFailure { error ->
                _signInState.update { it.copy(status = UiState.Error(error)) }
            }.onSuccess {
                _signInState.update { it.copy(status = UiState.Success(Unit)) }
            }
    }

    private fun signInWithGoogle(user: OAuthUser) = viewModelScope.launch {
        try {
            val token = user.token ?: throw AuthException.GoogleUserIsNull

            val newUser = User(
                id = token,
                email = user.email,
                name = user.name,
                image = UserImage(image = user.image, type = ImageType.URL)
            )

            signInWithGoogleUseCase(
                user = newUser,
                token = token,
                accessToken = user.accessToken
            )
                .onStart {
                    _signInState.update { it.copy(status = UiState.Loading) }
                }
                .onFailure { error ->
                    _signInState.update { it.copy(status = UiState.Error(error)) }
                }
                .onSuccess {
                    _signInState.update { it.copy(status = UiState.Success(Unit)) }
                }

        } catch (e: Exception) {
            _sideEffect.send(AuthContract.SideEffect.ShowError(e.message.toString()))
            return@launch
        }
    }

    private fun signInWithApple(firebaseUser: Result<FirebaseUser?>) = viewModelScope.launch {
        try {
            val user = firebaseUser.getOrThrow()
            val token =
                user?.getIdToken(true) ?: throw IllegalArgumentException("Apple token is null")

            val newUser = User(
                id = token,
                email = user.email,
                name = user.displayName,
                image = UserImage(image = user.photoURL, type = ImageType.URL)
            )

            signInWithAppleUseCase(newUser)
                .onStart {
                    _signInState.update { it.copy(status = UiState.Loading) }
                }
                .onFailure { error ->
                    _signInState.update { it.copy(status = UiState.Error(error)) }
                }
                .onSuccess {
                    _signInState.update { it.copy(status = UiState.Success(Unit)) }
                }

        } catch (e: Exception) {
            _sideEffect.send(AuthContract.SideEffect.ShowError(e.message.toString()))
            return@launch
        }
    }

    private fun signOut() = viewModelScope.launch {
        signOutUseCase()
            .onFailure {
                _sideEffect.send(AuthContract.SideEffect.SignOutFailure)
            }
            .onSuccess {
                _sideEffect.send(AuthContract.SideEffect.SignOutSuccess)
            }
    }

}