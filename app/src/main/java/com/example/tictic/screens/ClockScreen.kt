package com.example.tictic.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictic.components.NeumorphicCard
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ClockScreen() {

    var currentTime by remember { mutableStateOf("") }

    val availableCities = listOf(
        "Tokyo",
        "London",
        "New York",
        "Dubai",
        "Sydney",
        "Singapore"
    )

    val selectedCities = remember {
        mutableStateListOf(
            "Tokyo",
            "London"
        )
    }

    var showDialog by remember {
        mutableStateOf(false)
    }



    LaunchedEffect(Unit) {

        while (true) {

            currentTime =
                SimpleDateFormat(
                    "hh:mm:ss a",
                    Locale.getDefault()
                ).format(Date())

            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF16181D))
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            item {

                Text(
                    "TicTic",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(20.dp))

                NeumorphicCard {

                    Column {

                        Text(
                            currentTime,
                            color = Color.White,
                            fontSize = 38.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(8.dp))

                        Text(
                            "Chennai",
                            color = Color.Gray
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))





                Text(
                    "World Clocks",
                    color = Color.White,
                    fontSize = 22.sp
                )

                Spacer(Modifier.height(12.dp))
            }

            items(selectedCities) { city ->

                NeumorphicCard {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                city,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                "Timezone",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }

                        Text(
                            text = when(city) {
                                "Tokyo" -> getTimeForZone("Asia/Tokyo")
                                "London" -> getTimeForZone("Europe/London")
                                "New York" -> getTimeForZone("America/New_York")
                                "Dubai" -> getTimeForZone("Asia/Dubai")
                                "Sydney" -> getTimeForZone("Australia/Sydney")
                                "Singapore" -> getTimeForZone("Asia/Singapore")
                                else -> "--:--"
                            },

                            color = Color(0xFF7C8CFF)
                        )
                    }
                }

                Spacer(
                    Modifier.height(10.dp)
                )
            }
        }

        FloatingActionButton(
            onClick = {
                showDialog = true
            },

            modifier = Modifier
                .align(
                    Alignment.BottomEnd
                )
                .padding(20.dp),

            containerColor =
                Color(0xFF4C7DFF)
        ) {

            Icon(
                Icons.Default.Add,
                contentDescription = null
            )
        }
    }

    if (showDialog) {

        AlertDialog(

            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Add Timezone")
            },

            text = {

                Column {

                    availableCities.forEach { city ->

                        Text(
                            city,

                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                    if (!selectedCities.contains(city)) {
                                        selectedCities.add(city)
                                    }

                                    showDialog = false
                                }
                                .padding(12.dp)
                        )
                    }
                }
            },

            confirmButton = {}
        )
    }
}

fun getTimeForZone(zoneId: String): String {

    val formatter = SimpleDateFormat(
        "hh:mm:ss a",
        Locale.getDefault()
    )

    formatter.timeZone =
        TimeZone.getTimeZone(zoneId)

    return formatter.format(Date())
}