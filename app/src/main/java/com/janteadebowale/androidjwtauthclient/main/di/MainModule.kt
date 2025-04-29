package com.janteadebowale.androidjwtauthclient.main.di

import com.janteadebowale.androidjwtauthclient.SystemApplication
import com.janteadebowale.androidjwtauthclient.main.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
val mainModule = module {
    single<CoroutineScope> {
        (androidApplication() as SystemApplication).applicationCoroutineScope
    }
    viewModelOf(::MainViewModel)
}