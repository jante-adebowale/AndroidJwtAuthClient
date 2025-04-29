package com.janteadebowale.androidjwtauthclient.auth.data.remote

import com.janteadebowale.androidjwtauthclient.auth.domain.model.AuthMeResponse
import com.janteadebowale.androidjwtauthclient.auth.domain.model.LoginRequest
import com.janteadebowale.androidjwtauthclient.auth.domain.model.TokenResponse
import com.janteadebowale.androidjwtauthclient.auth.domain.model.RegistrationRequest
import com.janteadebowale.androidjwtauthclient.core.networking.ApiEndpoints
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
interface AuthApiService {
    @POST(ApiEndpoints.AUTH_URL)
    suspend fun login(@Body loginRequest: LoginRequest): Response<TokenResponse>

    @POST(ApiEndpoints.AUTH_REGISTER_URL)
    suspend fun register(@Body registrationRequest: RegistrationRequest): Response<TokenResponse>

    @GET(ApiEndpoints.AUTH_ME_URL)
    suspend fun authMe(): Response<AuthMeResponse>

    @GET(ApiEndpoints.AUTH_LOGOUT_URL)
    suspend fun logout(): Response<String>
}