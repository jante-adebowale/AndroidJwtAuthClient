package com.janteadebowale.androidjwtauthclient

import android.app.Application
import com.janteadebowale.androidjwtauthclient.auth.di.authModule
import com.janteadebowale.androidjwtauthclient.core.di.coreModule
import com.janteadebowale.androidjwtauthclient.core.networking.di.networkModule
import com.janteadebowale.androidjwtauthclient.home.di.homeModule
import com.janteadebowale.androidjwtauthclient.main.di.mainModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/
class SystemApplication : Application() {
    val applicationCoroutineScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SystemApplication)
            modules(
                mainModule,
                coreModule,
                authModule,
                networkModule,
                homeModule
            )
        }
    }
}