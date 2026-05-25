package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
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
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val isWide = maxWidth > 640.dp

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // Page Header Block
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Welcome Back,", color = SoftGray, fontSize = 13.sp)
                            Text(user?.name ?: "Developer", color = SoftWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                        Surface(
                            shape = CircleShape,
                            color = NeonCyan.copy(alpha = 0.15f),
                            modifier = Modifier.size(40.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, NeonCyan)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Person, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }

                item {
                    // --- RESPONSIVE LAYOUT DISPATCHER ---
                    if (isWide) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Left Section: Quick Tools & Projects
                            Column(
                                modifier = Modifier.weight(1.1f),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // AI Quick Tool Card
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { navController.navigate("assistant") },
                                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                                    shape = RoundedCornerShape(12.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(20.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(Icons.Default.Code, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(28.dp))
                                        Column(modifier = Modifier.padding(start = 14.dp)) {
                                            Text("Dr Canvas AI Assistant", color = SoftWhite, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                                            Text("Optimize, Debug, or Generate Code instantly with Gemini", color = SoftGray, fontSize = 12.sp)
                                        }
                                    }
                                }

                                // Recent Projects Subheader
                                Text(
                                    "Your Web Workspace Files",
                                    color = SoftWhite,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 8.dp)
                                )

                                if (projects.isEmpty()) {
                                    Card(
                                        colors = CardDefaults.cardColors(containerColor = DarkSurface.copy(alpha = 0.4f)),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            "No saved sandbox files yet. Use the '+' Floating Action Button or click 'Modern Code IDE' in the top bar to create your first script!",
                                            color = SoftGray,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(16.dp),
                                            lineHeight = 16.sp
                                        )
                                    }
                                } else {
                                    projects.forEach { project ->
                                        Card(
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = CardDefaults.cardColors(containerColor = DarkSurface),
                                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF192244))
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(16.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(Icons.Default.Terminal, contentDescription = null, tint = NeonCyan, modifier = Modifier.size(20.dp))
                                                Spacer(modifier = Modifier.width(12.dp))
                                                Column {
                                                    Text(project.title, color = NeonCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                                    Text(project.language, color = SoftGray, fontSize = 11.sp)
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            // Right Section: Workspace Diagnostics & Status Panel
                            Column(
                                modifier = Modifier.weight(0.9f),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                                    shape = RoundedCornerShape(12.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF1E293B)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(bottom = 12.dp)
                                        ) {
                                            Icon(Icons.Default.CloudDone, contentDescription = null, tint = Color(0xFF22C55E), modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text("Web Node Status", color = SoftWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        }
                                        
                                        StatusRow(label = "Primary URL", value = "drcanvas.ai/workspace")
                                        StatusRow(label = "Model Core", value = "gemini-3.5-flash")
                                        StatusRow(label = "Local Engine", value = "SQLite Room DB")
                                        StatusRow(label = "Developer seat", value = user?.email ?: "guest")
                                        StatusRow(label = "Total Sandbox Files", value = projects.size.toString())
                                        
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Text(
                                            "All systems operational. The sandbox workspace has direct access to browser compilers and high-fidelity local cache.",
                                            color = SoftGray,
                                            fontSize = 11.sp,
                                            lineHeight = 15.sp,
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        // --- MOBILE LAYOUT FOR DASHBOARD ---
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { navController.navigate("assistant") },
                                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Code, contentDescription = null, tint = NeonCyan)
                                    Column(modifier = Modifier.padding(start = 12.dp)) {
                                        Text("Dr Canvas AI Assistant", color = SoftWhite, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Text("Optimize, Debug, or Generate Code", color = SoftGray, fontSize = 12.sp)
                                    }
                                }
                            }

                            Text(
                                "Your Web Workspace Files",
                                color = SoftWhite,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            if (projects.isEmpty()) {
                                Text(
                                    "No saved sandbox files yet. Click code editor tab to begin!",
                                    color = SoftGray,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                )
                            } else {
                                projects.forEach { project ->
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = CardDefaults.cardColors(containerColor = DarkSurface)
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text(project.title, color = NeonCyan, fontWeight = FontWeight.Bold)
                                            Text(project.language, color = SoftGray, fontSize = 12.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    DeveloperFooter()
                }
            }
        }
    }
}

@Composable
fun StatusRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, color = SoftGray, fontSize = 11.sp)
        Text(
            text = value,
            color = SoftWhite,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}
