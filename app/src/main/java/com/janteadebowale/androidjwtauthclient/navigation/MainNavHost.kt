package com.janteadebowale.androidjwtauthclient.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.janteadebowale.androidjwtauthclient.auth.presentation.login.LoginRoute
import com.janteadebowale.androidjwtauthclient.auth.presentation.register.RegisterRoute
import com.janteadebowale.androidjwtauthclient.home.presentation.HomeRoute

/************************************************************
2025 Copyright (C), CodeInKotlin
https://www.janteadebowale.com | jante.adebowale@gmail.com
 ************************************************************
 * Author    : Jante Adebowale
 * Project   : AndroidJwtAuthClient
 * Youtube   : https://www.youtube.com/@jante-adebowale
 * Github    : https://github.com/jante-adebowale
 ************************************************************/

@Composable
fun MainNavHost(
    navController: NavHostController,
    destinationGraph: NavigationGraph,
) {
    NavHost(navController = navController, startDestination = destinationGraph) {
        authGraph(navController)
        homeGraph(navController)
    }
}

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<NavigationGraph.Auth>(startDestination = NavigationScreen.Login) {
        composable<NavigationScreen.Login>(
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(1000)
                )
            }) {
            LoginRoute(onRegister = {
                navController.navigate(NavigationScreen.Register)
            }, onNavigateToHome = {
                navController.navigate(NavigationGraph.Home) {
                    popUpTo(NavigationGraph.Auth) {
                        inclusive = true
                    }
                }
            })
        }
        composable<NavigationScreen.Register>(enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(1000)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                tween(1000)
            )
        }) {
            RegisterRoute(onNavUp = {
                navController.navigateUp()
            })
        }

    }
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<NavigationGraph.Home>(startDestination = NavigationScreen.Home) {
        composable<NavigationScreen.Home> {
           HomeRoute(onLogout = {
               navController.navigate(NavigationGraph.Auth) {
                   popUpTo(NavigationGraph.Home) {
                       inclusive = true
                   }
               }
           })

        }

    }
}