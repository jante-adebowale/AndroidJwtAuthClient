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
sealed interface LoginNavEvent {
    data object Success : LoginNavEvent
    data class Error(val errorMessage: String) : LoginNavEvent
}