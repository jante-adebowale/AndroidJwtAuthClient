package com.janteadebowale.androidjwtauthclient.auth.presentation.login

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface LoginUiEvent {
    data object OnLoginClicked : LoginUiEvent
    data class OnUsernameChanged(val username: String) : LoginUiEvent
    data class OnPasswordChanged(val password: String) : LoginUiEvent
}