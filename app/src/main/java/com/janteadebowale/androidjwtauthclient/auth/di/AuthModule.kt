package com.janteadebowale.androidjwtauthclient.auth.di

import com.janteadebowale.androidjwtauthclient.auth.data.remote.AuthApiService
import com.janteadebowale.androidjwtauthclient.auth.data.repository.AuthRepositoryImpl
import com.janteadebowale.androidjwtauthclient.auth.domain.repository.AuthRepository
import com.janteadebowale.androidjwtauthclient.auth.presentation.login.LoginViewModel
import com.janteadebowale.androidjwtauthclient.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/


fun providesAuthApiService(retrofit: Retrofit): AuthApiService =
    retrofit.create(AuthApiService::class.java)


val authModule = module {
    singleOf(::providesAuthApiService)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}