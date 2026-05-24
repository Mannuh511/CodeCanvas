package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.NeonCyan

import com.example.ui.components.DeveloperFooter

@Composable
fun AiAssistantScreen(viewModel: MainViewModel) {
    val chatHistory by viewModel.chatHistory.collectAsState()
    val isLoading by viewModel.isChatLoading.collectAsState()
    var currentMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(com.example.ui.theme.DeepBlue)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = NeonCyan)
            Text(" CodeCanvas AI Tutor", color = SoftWhite, fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = false
        ) {
            items(chatHistory) { msg ->
                val isUser = msg.role == "user"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (isUser) DarkSurfaceVariant else DarkSurface
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = msg.text,
                            color = SoftWhite,
                            modifier = Modifier.padding(12.dp),
                            fontFamily = if (!isUser) FontFamily.Monospace else FontFamily.Default
                        )
                    }
                }
            }
            if (isLoading) {
                item {
                    CircularProgressIndicator(
                        color = NeonCyan,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = currentMessage,
                onValueChange = { currentMessage = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Ask for code optimization, debugging, algorithms...") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonCyan,
                    focusedTextColor = SoftWhite,
                    unfocusedTextColor = SoftWhite
                )
            )
            IconButton(
                onClick = {
                    if (currentMessage.isNotBlank() && !isLoading) {
                        viewModel.sendChatMessage(currentMessage)
                        currentMessage = ""
                    }
                }
            ) {
                Icon(Icons.Default.Send, contentDescription = "Send", tint = NeonCyan)
            }
        }
        DeveloperFooter()
    }
}
