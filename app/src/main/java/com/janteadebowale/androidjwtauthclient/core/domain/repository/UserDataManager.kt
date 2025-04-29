package com.janteadebowale.androidjwtauthclient.core.domain.repository

import com.janteadebowale.androidjwtauthclient.core.domain.model.UserConfig
import com.janteadebowale.androidjwtauthclient.core.domain.model.ThemeConfig
import kotlinx.coroutines.flow.Flow

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
interface UserDataManager {
    suspend fun saveUsername(username: String)

    fun getUsername(): Flow<String>

    suspend fun setLoggedIn(value: Boolean)

    suspend fun setUserTheme(themeConfig: ThemeConfig)

    fun getSystemUser(): Flow<UserConfig>
}