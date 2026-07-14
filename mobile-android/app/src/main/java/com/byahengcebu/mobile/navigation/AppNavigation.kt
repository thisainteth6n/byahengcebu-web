package com.byahengcebu.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.byahengcebu.mobile.screens.auth.LoginScreen
import com.byahengcebu.mobile.screens.auth.RegisterScreen
import com.byahengcebu.mobile.screens.dashboard.DashboardScreen
import com.byahengcebu.mobile.screens.dashboard.VehicleDetailsScreen
import com.byahengcebu.mobile.screens.trip.TripScreen
import com.byahengcebu.mobile.viewmodel.TripViewModel
import com.byahengcebu.mobile.viewmodel.VehicleViewModel

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

                onVehicleClick = {

                    vehicleViewModel.selectVehicle(it)

                    navController.navigate(Routes.VehicleDetails.route)

                },

                onTripClick = {

                    navController.navigate(Routes.Trip.route)

                }

            )

        }

        composable(Routes.VehicleDetails.route) {

            VehicleDetailsScreen(

                viewModel = vehicleViewModel,

                onBackClick = {

                    navController.popBackStack()

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