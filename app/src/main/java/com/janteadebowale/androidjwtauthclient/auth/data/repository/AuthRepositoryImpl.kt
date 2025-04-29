package com.janteadebowale.androidjwtauthclient.auth.data.repository

import com.janteadebowale.androidjwtauthclient.auth.data.remote.AuthApiService
import com.janteadebowale.androidjwtauthclient.auth.domain.model.AuthMeResponse
import com.janteadebowale.androidjwtauthclient.auth.domain.model.LoginRequest
import com.janteadebowale.androidjwtauthclient.auth.domain.model.RegistrationRequest
import com.janteadebowale.androidjwtauthclient.auth.domain.repository.AuthRepository
import com.janteadebowale.androidjwtauthclient.core.common.DataResult
import com.janteadebowale.androidjwtauthclient.core.domain.model.AuthToken
import com.janteadebowale.androidjwtauthclient.core.domain.repository.SessionManager
import com.janteadebowale.androidjwtauthclient.core.networking.safeNetworkCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val coroutineScope: CoroutineScope,
    private val sessionManager: SessionManager
) : AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): DataResult<Unit> {
        val loginResponse = safeNetworkCall {
            authApiService.login(loginRequest)
        }
        if (loginResponse.isFailure()) {
            return DataResult.Failure(loginResponse.getError())
        }
        coroutineScope.launch {
            val result = loginResponse.getSuccessData()
            sessionManager.set(
                AuthToken(
                    accessToken = result.accessToken,
                    refreshToken = result.refreshToken
                )
            )
        }
        return DataResult.Success(Unit)
    }

    override suspend fun register(registrationRequest: RegistrationRequest): DataResult<Unit> {
        val response = safeNetworkCall {
            authApiService.register(registrationRequest)
        }

        if (response.isFailure()) {
            return DataResult.Failure(response.getError())
        }
        return DataResult.Success(Unit)
    }

    override suspend fun authMe(): DataResult<AuthMeResponse> {
        val response = safeNetworkCall {
            authApiService.authMe()
        }
        if (response.isFailure()) {
            return DataResult.Failure(response.getError())
        }
        return DataResult.Success(response.getSuccessData())
    }


}