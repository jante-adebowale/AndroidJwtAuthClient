package com.janteadebowale.androidjwtauthclient.core.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.janteadebowale.androidjwtauthclient.core.data.repository.EncryptedSessionManager
import com.janteadebowale.androidjwtauthclient.core.data.repository.UserDataManagerImpl
import com.janteadebowale.androidjwtauthclient.core.domain.repository.SessionManager
import com.janteadebowale.androidjwtauthclient.core.domain.repository.UserDataManager
import org.koin.android.ext.koin.androidApplication
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

val coreModule = module {

    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_token_pref",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    single<SessionManager> {
        EncryptedSessionManager(get())
    }

    single<UserDataManager> {
        UserDataManagerImpl(get())
    }
}