package com.janteadebowale.androidjwtauthclient.auth.domain.model

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
data class AuthMeResponse(
    val name: String,
    val phone: String,
    val sessionId:String
)
