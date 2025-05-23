package com.janteadebowale.androidjwtauthclient.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.rememberNavController
import com.janteadebowale.androidjwtauthclient.core.domain.model.ThemeConfig
import com.janteadebowale.androidjwtauthclient.core.presentation.designsystem.theme.AndroidJwtAuthClientTheme
import com.janteadebowale.androidjwtauthclient.navigation.MainNavHost
import com.janteadebowale.androidjwtauthclient.navigation.NavigationGraph
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        var uiState: MainUiState by mutableStateOf(MainUiState.Loading)

        // Update the uiState
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach { uiState = it }
                    .collect()
            }
        }

        //Display splash screen while loading
        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                is MainUiState.Loading -> true
                is MainUiState.Success -> false
            }
        }

        enableEdgeToEdge()

        setContent {
            val darkTheme = isDarkTheme(uiState)
            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        android.graphics.Color.TRANSPARENT,
                        android.graphics.Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        lightScrim,
                    ) {
                        darkTheme
                    }
                )
                onDispose {}
            }
            AndroidJwtAuthClientTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val navHostController = rememberNavController()
                    if (uiState != MainUiState.Loading) {
                        val successData: MainUiState.Success = uiState as MainUiState.Success
                        MainNavHost(
                            navHostController,
                            destinationGraph = if (successData.userConfig.isLoggedIn) NavigationGraph.Home else NavigationGraph.Auth
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun isDarkTheme(uiState: MainUiState): Boolean {
    return when (uiState) {
        is MainUiState.Loading -> isSystemInDarkTheme()
        is MainUiState.Success -> when (uiState.userConfig.theme) {
            ThemeConfig.LIGHT -> false
            ThemeConfig.DARK -> true
            ThemeConfig.SYSTEM_DEFAULT -> isSystemInDarkTheme()
        }
    }
}

private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

