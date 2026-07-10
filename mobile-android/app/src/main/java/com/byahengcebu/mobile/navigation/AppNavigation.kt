package com.byahengcebu.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.byahengcebu.mobile.screens.auth.LoginScreen
import com.byahengcebu.mobile.screens.auth.RegisterScreen
import com.byahengcebu.mobile.screens.dashboard.DashboardScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(

        navController = navController,

        startDestination = Routes.Login.route

    ) {

        composable(Routes.Login.route) {

            LoginScreen(

                onLoginClick = {

                    navController.navigate(Routes.Dashboard.route)

                },

                onRegisterClick = {

                    navController.navigate(Routes.Register.route)

                }

            )

        }

        composable(Routes.Register.route) {

            RegisterScreen(

                onRegisterSuccess = {

                    navController.popBackStack()

                },

                onBackClick = {

                    navController.popBackStack()

                }

            )

        }

        composable(Routes.Dashboard.route) {

            DashboardScreen()

        }

    }

}