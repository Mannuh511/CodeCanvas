package com.example.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.SoftWhite

@Composable
fun CodeCanvasBottomNav(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = com.example.ui.theme.DeepBlue
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Dashboard, contentDescription = "Dashboard") },
            label = { Text("Dashboard") },
            selected = currentRoute == "dashboard",
            onClick = {
                if (currentRoute != "dashboard") {
                    navController.navigate("dashboard") { popUpTo("dashboard") { inclusive = false } }
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NeonCyan,
                unselectedIconColor = SoftWhite,
                selectedTextColor = NeonCyan,
                unselectedTextColor = SoftWhite,
                indicatorColor = com.example.ui.theme.DarkSurfaceVariant
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AutoAwesome, contentDescription = "AI Assistant") },
            label = { Text("AI") },
            selected = currentRoute == "assistant",
            onClick = {
                if (currentRoute != "assistant") {
                     navController.navigate("assistant")
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NeonCyan,
                unselectedIconColor = SoftWhite,
                selectedTextColor = NeonCyan,
                unselectedTextColor = SoftWhite,
                indicatorColor = com.example.ui.theme.DarkSurfaceVariant
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Code, contentDescription = "Editor") },
            label = { Text("Editor") },
            selected = currentRoute == "editor",
            onClick = {
                if (currentRoute != "editor") {
                    navController.navigate("editor")
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NeonCyan,
                unselectedIconColor = SoftWhite,
                selectedTextColor = NeonCyan,
                unselectedTextColor = SoftWhite,
                indicatorColor = com.example.ui.theme.DarkSurfaceVariant
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.RateReview, contentDescription = "Reviews") },
            label = { Text("Reviews") },
            selected = currentRoute == "reviews",
            onClick = {
                if (currentRoute != "reviews") {
                    navController.navigate("reviews")
                }
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = NeonCyan,
                unselectedIconColor = SoftWhite,
                selectedTextColor = NeonCyan,
                unselectedTextColor = SoftWhite,
                indicatorColor = com.example.ui.theme.DarkSurfaceVariant
            )
        )
    }
}
