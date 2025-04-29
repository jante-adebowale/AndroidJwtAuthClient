package com.janteadebowale.androidjwtauthclient.navigation

import kotlinx.serialization.Serializable

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface NavigationScreen {
    @Serializable
    data object Login : NavigationScreen

    @Serializable
    data object Register : NavigationScreen

    @Serializable
    data object Home : NavigationScreen

}