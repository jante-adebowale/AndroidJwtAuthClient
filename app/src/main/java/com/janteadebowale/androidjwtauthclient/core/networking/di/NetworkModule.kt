package com.janteadebowale.androidjwtauthclient.core.networking.di

import com.janteadebowale.androidjwtauthclient.auth.data.remote.AuthApiService
import com.janteadebowale.androidjwtauthclient.core.networking.ApiEndpoints
import com.janteadebowale.androidjwtauthclient.core.networking.refreshtoken.NetworkRequestInterceptor
import com.janteadebowale.androidjwtauthclient.core.networking.refreshtoken.TokenApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/

fun providesLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun providesTokenApiService(retrofit: Retrofit): TokenApiService =
    retrofit.create(TokenApiService::class.java)

fun provideHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkRequestInterceptor: NetworkRequestInterceptor,
): OkHttpClient {
    return OkHttpClient
        .Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(networkRequestInterceptor)
        .build()
}

fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(ApiEndpoints.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

val networkModule = module {
    singleOf(::providesTokenApiService)
    singleOf(::NetworkRequestInterceptor)
    singleOf(::providesLoggingInterceptor)
    singleOf(::provideHttpClient)
    singleOf(::provideConverterFactory)
    singleOf(::provideRetrofit)
}