package com.janteadebowale.androidjwtauthclient.auth.presentation.register


/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface RegisterUiEvent {
    data object OnRegisterClicked : RegisterUiEvent
    data class OnNameChanged(val name: String) : RegisterUiEvent
    data class OnPhoneChanged(val phone: String) : RegisterUiEvent
    data class OnPasswordChanged(val password: String) : RegisterUiEvent
    data class OnConfirmPasswordChanged(val confirmPassword: String) : RegisterUiEvent
}