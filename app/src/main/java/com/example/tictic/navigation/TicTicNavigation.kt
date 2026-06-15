package com.example.tictic.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tictic.screens.ClockScreen
import com.example.tictic.screens.StopwatchScreen
import com.example.tictic.screens.TimerScreen

@Composable
fun TicTicNavigation(
    navController: NavHostController
) {

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Clock.route,
            modifier = Modifier.padding(padding)
        ) {

            composable(Screen.Clock.route) {
                ClockScreen()
            }

            composable(Screen.Stopwatch.route) {
                StopwatchScreen()
            }

            composable(Screen.Timer.route) {
                TimerScreen()
            }
        }
    }
}