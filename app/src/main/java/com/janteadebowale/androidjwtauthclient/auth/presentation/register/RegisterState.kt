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
data class RegisterState(
    val isLoading:Boolean = false,
    var canRegister: Boolean = false,
    val name:String = "",
    val phone:String = "",
    val password:String = "",
    val confirmPassword:String = "",
)
