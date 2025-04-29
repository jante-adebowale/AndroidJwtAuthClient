package com.janteadebowale.androidjwtauthclient.auth.presentation.register

import com.janteadebowale.androidjwtauthclient.auth.presentation.login.LoginNavEvent

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface RegisterNavEvent {
    data class Success(val message: String) : RegisterNavEvent
    data class Error(val errorMessage: String) : RegisterNavEvent
}