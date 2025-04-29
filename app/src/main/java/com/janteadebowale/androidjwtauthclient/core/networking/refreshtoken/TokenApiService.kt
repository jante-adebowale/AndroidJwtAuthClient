package com.janteadebowale.androidjwtauthclient.core.networking.refreshtoken

import com.janteadebowale.androidjwtauthclient.core.networking.ApiEndpoints
import retrofit2.Response
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
interface TokenApiService {
    @GET(ApiEndpoints.AUTH_REFRESH_URL)
    suspend fun refreshToken(@HeaderMap headers: Map<String, String>): Response<RefreshTokenResponse>
}