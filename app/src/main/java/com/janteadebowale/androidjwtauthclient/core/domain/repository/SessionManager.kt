package com.janteadebowale.androidjwtauthclient.core.domain.repository

import com.janteadebowale.androidjwtauthclient.core.domain.model.AuthToken

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
interface SessionManager {
    suspend fun set(authToken: AuthToken?)

    suspend fun get(): AuthToken?

    //Clear on logout
    suspend fun clear()
}