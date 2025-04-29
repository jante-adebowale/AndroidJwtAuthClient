package com.janteadebowale.androidjwtauthclient.home.presentation

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface HomeNavEvent {
    data object OnLogout : HomeNavEvent
    data class OnError(val errorMessage:String,val code:String = "") : HomeNavEvent
}