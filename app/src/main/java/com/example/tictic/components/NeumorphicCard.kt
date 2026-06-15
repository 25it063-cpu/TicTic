package com.example.tictic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NeumorphicCard(
    content: @Composable () -> Unit
) {

    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B1E25)
        ),
        modifier = Modifier
            .border(
                1.dp,
                Color(0xFF2A2F3A),
                RoundedCornerShape(28.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF1B1E25))
                .padding(20.dp)
        ) {
            content()
        }
    }
}