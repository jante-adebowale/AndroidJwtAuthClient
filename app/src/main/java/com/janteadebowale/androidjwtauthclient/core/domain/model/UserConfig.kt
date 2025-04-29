package com.janteadebowale.androidjwtauthclient.core.domain.model

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
data class UserConfig(
    val username: String,
    val isLoggedIn: Boolean,
    val theme: ThemeConfig
)
