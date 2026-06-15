package com.example.tictic.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        Screen.Clock,
        Screen.Stopwatch,
        Screen.Timer
    )

    NavigationBar {

        val navBackStackEntry =
            navController.currentBackStackEntryAsState()

        val currentRoute =
            navBackStackEntry.value?.destination?.route

        screens.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,

                onClick = {
                    navController.navigate(screen.route)
                },

                icon = {
                    when (screen.route) {

                        "clock" ->
                            Icon(
                                Icons.Default.AccessTime,
                                null
                            )

                        "stopwatch" ->
                            Icon(
                                Icons.Default.Timer,
                                null
                            )

                        else ->
                            Icon(
                                Icons.Default.AvTimer,
                                null
                            )
                    }
                },

                label = {
                    Text(screen.title)
                }
            )
        }
    }
}