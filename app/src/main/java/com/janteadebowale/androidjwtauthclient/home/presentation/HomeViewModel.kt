package com.janteadebowale.androidjwtauthclient.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janteadebowale.androidjwtauthclient.auth.domain.repository.AuthRepository
import com.janteadebowale.androidjwtauthclient.core.common.DataResult
import com.janteadebowale.androidjwtauthclient.core.domain.model.ThemeConfig
import com.janteadebowale.androidjwtauthclient.core.domain.repository.SessionManager
import com.janteadebowale.androidjwtauthclient.core.domain.repository.UserDataManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
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
class HomeViewModel(
    private val authRepository: AuthRepository,
    private val userDataManager: UserDataManager,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _homeNavChannel = Channel<HomeNavEvent>()
    val homeNavChannel = _homeNavChannel.receiveAsFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.Logout -> {
                logout()
            }

            HomeUiEvent.LoadBioData -> {
                loadBioData()
            }

            HomeUiEvent.DarkMode -> {
                setTheme(ThemeConfig.DARK)
            }

            HomeUiEvent.LightMode -> {
                setTheme(ThemeConfig.LIGHT)
            }

            HomeUiEvent.SystemDefault -> {
                setTheme(ThemeConfig.SYSTEM_DEFAULT)
            }
        }
    }

    private fun setTheme(selectedTheme: ThemeConfig) {
        viewModelScope.launch {
            userDataManager.setUserTheme(selectedTheme)
        }
    }

    private fun loadBioData() {
      viewModelScope.launch {
          _state.update {
              it.copy(isLoading = true)
          }
          val result = authRepository.authMe()

          _state.update {
              it.copy(isLoading = false)
          }


          when(result){
              is DataResult.Failure -> {
                  _homeNavChannel.send(HomeNavEvent.OnError(result.getError().message, code = result.getError().code))
              }
              is DataResult.Success -> {
                 val authMeValue =  result.getSuccessData()
                  _state.update {
                      it.copy(name = authMeValue.name, phone = authMeValue.phone, sessionId = authMeValue.sessionId)
                  }
              }
          }
      }
    }

    private fun logout() {
        viewModelScope.launch {
            sessionManager.clear()
            userDataManager.setLoggedIn(false)
          //  _homeNavChannel.send(HomeNavEvent.OnLogout)
        }
    }
}