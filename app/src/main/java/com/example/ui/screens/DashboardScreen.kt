package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui.MainViewModel
import com.example.ui.components.DeveloperFooter
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.NeonCyan

@Composable
fun DashboardScreen(navController: NavController, viewModel: MainViewModel) {
    val user by viewModel.currentUser.collectAsState()
    val projects by viewModel.allProjects.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("editor") },
                containerColor = NeonCyan
            ) {
                Icon(Icons.Default.Add, contentDescription = "New Project", tint = DeepBlue)
            }
        },
        containerColor = DeepBlue
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Welcome Back,", color = SoftGray, fontSize = 14.sp)
                        Text(user?.name ?: "Developer", color = SoftWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Icon(Icons.Default.Person, contentDescription = null, tint = NeonCyan)
                }
            }

            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable { navController.navigate("assistant") },
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Code, contentDescription = null, tint = NeonCyan)
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            Text("AI Assistant Tutor", color = SoftWhite, fontWeight = FontWeight.Bold)
                            Text("Optimize, Debug, or Generate Code", color = SoftGray, fontSize = 12.sp)
                        }
                    }
                }
            }

            item {
                Text(
                    "Recent Projects",
                    color = SoftWhite,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
                )
            }

            if (projects.isEmpty()) {
                item {
                    Text(
                        "No saved projects yet. Start coding!",
                        color = SoftGray,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            } else {
                items(projects) { project ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkSurface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(project.title, color = NeonCyan, fontWeight = FontWeight.Bold)
                            Text(project.language, color = SoftGray, fontSize = 12.sp)
                        }
                    }
                }
            }

            item {
                DeveloperFooter()
            }
        }
    }
}
