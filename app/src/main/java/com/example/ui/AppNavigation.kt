package com.example.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.components.WebBrowserLayout
import com.example.ui.screens.AiAssistantScreen
import com.example.ui.screens.CodeEditorScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.ReviewsScreen

@Composable
fun AppNavigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val currentUser by viewModel.currentUser.collectAsState()
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    WebBrowserLayout(
        currentRoute = currentRoute,
        currentUserEmail = currentUser?.email,
        onNavigate = { route ->
            if (currentRoute != route) {
                navController.navigate(route) {
                    // Prevent piling up navigation backstack units
                    popUpTo("dashboard") { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        onLogout = {
            viewModel.logout()
            navController.navigate("login") {
                popUpTo("login") { inclusive = true }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.fillMaxSize(),
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)) }
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("dashboard") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    viewModel = viewModel
                )
            }
            composable("dashboard") {
                DashboardScreen(navController = navController, viewModel = viewModel)
            }
            composable("assistant") {
                AiAssistantScreen(viewModel = viewModel)
            }
            composable("editor") {
                CodeEditorScreen(viewModel = viewModel)
            }
            composable("reviews") {
                ReviewsScreen(viewModel = viewModel)
            }
        }
    }
}

