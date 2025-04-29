package com.janteadebowale.androidjwtauthclient.auth.domain.repository

import com.janteadebowale.androidjwtauthclient.auth.domain.model.AuthMeResponse
import com.janteadebowale.androidjwtauthclient.auth.domain.model.LoginRequest
import com.janteadebowale.androidjwtauthclient.auth.domain.model.RegistrationRequest
import com.janteadebowale.androidjwtauthclient.core.common.DataResult

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): DataResult<Unit>

    suspend fun register(registrationRequest: RegistrationRequest): DataResult<Unit>

    suspend fun authMe():DataResult<AuthMeResponse>

}