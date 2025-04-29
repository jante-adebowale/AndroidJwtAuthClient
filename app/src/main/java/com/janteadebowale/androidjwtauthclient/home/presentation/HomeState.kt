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
data class HomeState(
    val isLoading: Boolean = false,
    val name:String = "",
    val phone:String = "",
    val sessionId:String = "",
    val isError: Boolean = false
)
