package com.example.tictic.navigation

sealed class Screen(
    val route: String,
    val title: String
) {

    object Clock : Screen(
        "clock",
        "Clock"
    )

    object Stopwatch : Screen(
        "stopwatch",
        "Stopwatch"
    )

    object Timer : Screen(
        "timer",
        "Timer"
    )
}