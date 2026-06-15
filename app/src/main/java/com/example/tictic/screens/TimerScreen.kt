package com.example.tictic.screens

import android.media.Ringtone
import android.media.RingtoneManager
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.material3.OutlinedTextFieldDefaults

@Composable
fun TimerScreen() {

    val context = LocalContext.current

    var hours by remember { mutableStateOf("0") }
    var minutes by remember { mutableStateOf("5") }
    var seconds by remember { mutableStateOf("0") }

    var totalSeconds by remember { mutableStateOf(0) }
    var initialSeconds by remember { mutableStateOf(0) }

    var isRunning by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var ringtone by remember {
        mutableStateOf<Ringtone?>(null)
    }

    LaunchedEffect(isRunning) {

        while (isRunning && totalSeconds > 0) {

            delay(1000)

            totalSeconds--
        }

        if (isRunning && totalSeconds == 0 && initialSeconds > 0) {

            isRunning = false

            val alarmUri = RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_ALARM
            ) ?: RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_NOTIFICATION
            )

            ringtone = RingtoneManager.getRingtone(
                context,
                alarmUri
            )

            ringtone?.play()

            showDialog = true
        }
    }

    val progress =
        if (initialSeconds == 0)
            1f
        else
            totalSeconds.toFloat() / initialSeconds.toFloat()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF16181D))
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Timer",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            contentAlignment = Alignment.Center
        ) {

            Canvas(
                modifier = Modifier.size(260.dp)
            ) {

                drawArc(
                    color = Color.DarkGray,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(
                        width = 22f,
                        cap = StrokeCap.Round
                    )
                )

                drawArc(
                    color = Color(0xFF6F8CFF),
                    startAngle = -90f,
                    sweepAngle = progress * 360f,
                    useCenter = false,
                    style = Stroke(
                        width = 22f,
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(
                text = formatTime(totalSeconds),
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = hours,
            onValueChange = { hours = it },
            label = { Text("Hours") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF6F8CFF),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF6F8CFF),
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color(0xFF6F8CFF)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = minutes,
            onValueChange = { minutes = it },
            label = { Text("Minutes") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF6F8CFF),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF6F8CFF),
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color(0xFF6F8CFF)
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = seconds,
            onValueChange = { seconds = it },
            label = { Text("Seconds") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF6F8CFF),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xFF6F8CFF),
                unfocusedLabelColor = Color.Gray,
                cursorColor = Color(0xFF6F8CFF)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {

            Button(
                onClick = {

                    totalSeconds =
                        (hours.toIntOrNull() ?: 0) * 3600 +
                                (minutes.toIntOrNull() ?: 0) * 60 +
                                (seconds.toIntOrNull() ?: 0)

                    initialSeconds = totalSeconds

                    isRunning = true
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6F8CFF)
                )
            ) {
                Text("Start")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = {
                    isRunning = false
                }
            ) {
                Text("Pause")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                isRunning = false

                totalSeconds = 0
                initialSeconds = 0

                ringtone?.stop()
            }
        ) {
            Text("Reset")
        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = {},

            title = {
                Text("⏰ Time's Up!")
            },

            text = {
                Text(
                    "Your timer has finished."
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        ringtone?.stop()

                        showDialog = false
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

fun formatTime(totalSeconds: Int): String {

    val hours = totalSeconds / 3600

    val minutes =
        (totalSeconds % 3600) / 60

    val seconds =
        totalSeconds % 60

    return String.format(
        "%02d:%02d:%02d",
        hours,
        minutes,
        seconds
    )
}