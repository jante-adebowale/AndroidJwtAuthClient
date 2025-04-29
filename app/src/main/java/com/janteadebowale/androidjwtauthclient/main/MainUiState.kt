package com.janteadebowale.androidjwtauthclient.main

import com.janteadebowale.androidjwtauthclient.core.domain.model.UserConfig

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val userConfig: UserConfig) : MainUiState
}