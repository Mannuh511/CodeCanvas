package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.SoftGray

@Composable
fun DeveloperFooter() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkSurfaceVariant)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "© CodeCanvas. All Rights Reserved. Unauthorized modification prohibited.",
            color = MaterialTheme.colorScheme.error,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Created and Developed by: Mannuh",
            color = SoftGray,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Email: emmanuelmicheni8@gmail.com | Phone: 0114025039",
            color = SoftGray,
            fontSize = 11.sp
        )
        Text(
            text = "Location: Meru, Kenya (Admin-Only Control System Active)",
            color = SoftGray,
            fontSize = 11.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "WhatsApp | Email | GitHub | LinkedIn",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
