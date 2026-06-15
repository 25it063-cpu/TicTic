package com.example.tictic.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.animation.core.animateFloatAsState

@Composable
fun StopwatchScreen() {

    var time by remember {
        mutableStateOf(0L)
    }

    var isRunning by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isRunning) {

        while (isRunning) {
            delay(10)
            time += 10
        }
    }

    val targetProgress =
        ((time / 1000f) % 60f) / 60f

    val progress by animateFloatAsState(
        targetValue = targetProgress,
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF16181D))
            .padding(24.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally,

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            "Stopwatch",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            Modifier.height(40.dp)
        )

        Box(
            contentAlignment =
                Alignment.Center
        ) {

            Canvas(
                modifier =
                    Modifier.size(280.dp)
            ) {

                drawArc(
                    color = Color.DarkGray,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,

                    style = Stroke(
                        width = 24f,
                        cap = StrokeCap.Round
                    )
                )

                drawArc(
                    color = Color(0xFF6F8CFF),
                    startAngle = -90f,
                    sweepAngle = progress * 360f,
                    useCenter = false,

                    style = Stroke(
                        width = 24f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = formatStopwatch(time),
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            Modifier.height(40.dp)
        )

        Row {

            Button(
                onClick = {
                    isRunning = true
                },

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Color(0xFF6F8CFF)
                    )
            ) {
                Text("Start")
            }

            Spacer(
                Modifier.width(12.dp)
            )

            Button(
                onClick = {
                    isRunning = false
                }
            ) {
                Text("Pause")
            }
        }

        Spacer(
            Modifier.height(12.dp)
        )

        Button(
            onClick = {

                isRunning = false
                time = 0
            }
        ) {
            Text("Reset")
        }
    }
}

fun formatStopwatch(
    time: Long
): String {

    val millis =
        (time % 1000) / 10

    val seconds =
        (time / 1000) % 60

    val minutes =
        (time / 60000)

    return String.format(
        "%02d:%02d.%02d",
        minutes,
        seconds,
        millis
    )
}