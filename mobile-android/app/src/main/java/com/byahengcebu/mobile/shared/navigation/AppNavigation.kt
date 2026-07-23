package com.byahengcebu.mobile.shared.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.byahengcebu.mobile.features.auth.screens.LoginScreen
import com.byahengcebu.mobile.features.auth.screens.RegisterScreen
import com.byahengcebu.mobile.features.dashboard.screens.DashboardScreen
import com.byahengcebu.mobile.features.trip.screens.TripScreen
import com.byahengcebu.mobile.features.trip.viewmodel.TripViewModel
import com.byahengcebu.mobile.features.vehicle.viewmodel.VehicleViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val vehicleViewModel: VehicleViewModel = viewModel()

    val tripViewModel: TripViewModel = viewModel()

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

            DashboardScreen(

                viewModel = vehicleViewModel,

                onTripClick = {

                    navController.navigate(Routes.Trip.route)

                },

                onLogout = {

                    navController.navigate(Routes.Login.route) {

                        popUpTo(0)

                    }

                }

            )

        }

        composable(Routes.Trip.route) {

            TripScreen(

                viewModel = tripViewModel

            )

        }

    }

}