package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.NeonCyan

import com.example.ui.components.DeveloperFooter

@Composable
fun ReviewsScreen(viewModel: MainViewModel) {
    val reviews by viewModel.allReviews.collectAsState()
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableIntStateOf(5) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
    ) {
        Text("Community Reviews", color = SoftWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Card(colors = CardDefaults.cardColors(containerColor = DarkSurface), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Write a Review or Suggestion", color = SoftWhite)
                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    for (i in 1..5) {
                        IconButton(onClick = { rating = i }) {
                            Icon(
                                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.StarBorder,
                                contentDescription = null,
                                tint = NeonCyan
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = NeonCyan,
                        focusedTextColor = SoftWhite,
                        unfocusedTextColor = SoftWhite
                    ),
                    placeholder = { Text("Suggest features or report bugs...") }
                )
                Button(
                    onClick = {
                        if (reviewText.isNotBlank()) {
                            viewModel.submitReview(reviewText, rating)
                            reviewText = ""
                        }
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NeonCyan)
                ) {
                    Text("Submit Review", color = DeepBlue, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(reviews) { r ->
                Card(colors = CardDefaults.cardColors(containerColor = DarkSurface), modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text(r.userEmail, color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Row {
                                for(i in 1..r.rating) {
                                    Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.height(16.dp))
                                }
                            }
                        }
                        Text(r.content, color = SoftWhite, modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }
            item {
                DeveloperFooter()
            }
        }
    }
}
