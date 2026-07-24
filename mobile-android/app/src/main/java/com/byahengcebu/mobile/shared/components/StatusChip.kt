package com.byahengcebu.mobile.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StatusChip(
    status: String
) {

    val background = when (status.uppercase()) {

        "ONGOING" ->
            Color(0xFF1976D2)

        "COMPLETED" ->
            Color(0xFF2E7D32)

        "PENDING" ->
            Color(0xFFF9A825)

        "VERIFIED" ->
            Color(0xFF2E7D32)

        "REJECTED" ->
            Color(0xFFC62828)

        else ->
            MaterialTheme.colorScheme.secondary

    }

    Text(

        text = status,

        color = Color.White,

        fontWeight = FontWeight.Bold,

        modifier = Modifier
            .background(
                background,
                RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 14.dp,
                vertical = 6.dp
            )

    )

}