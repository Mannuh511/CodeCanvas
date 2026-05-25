package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.SoftGray
import com.example.ui.theme.SoftWhite

@Composable
fun WebBrowserLayout(
    currentRoute: String?,
    onNavigate: (String) -> Unit,
    currentUserEmail: String?,
    onLogout: () -> Unit,
    content: @Composable () -> Unit
) {
    val dynamicUrl = when (currentRoute) {
        "login" -> "https://drcanvas.ai/auth/login"
        "dashboard" -> "https://drcanvas.ai/dashboard"
        "assistant" -> "https://drcanvas.ai/workspace/assistant-tutor"
        "editor" -> "https://drcanvas.ai/workspace/web-ide"
        "reviews" -> "https://drcanvas.ai/community/reviews"
        else -> "https://drcanvas.ai/"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        // --- 1. SIMULATED BROWSER CONTROLS (CHROME WINDOW THEME) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F172A)) // Slate 900
        ) {
            // macOS-Style Window Bars and Tab List
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // macOS window controls (Red, Yellow, Green)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    Box(modifier = Modifier.size(10.dp).background(Color(0xFFEF4444), CircleShape))
                    Box(modifier = Modifier.size(10.dp).background(Color(0xFFF59E0B), CircleShape))
                    Box(modifier = Modifier.size(10.dp).background(Color(0xFF22C55E), CircleShape))
                }

                // Dynamic Browser Tab representation
                Card(
                    colors = CardDefaults.cardColors(containerColor = DeepBlue),
                    shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
                    modifier = Modifier
                        .height(28.dp)
                        .widthIn(max = 180.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Dr Canvas - Web",
                            color = SoftWhite,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Tab",
                            tint = SoftGray,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(6.dp))

                // New Tab button
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Tab",
                    tint = SoftGray,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Workspace Status Alert
                Surface(
                    color = Color(0xFF1E293B).copy(alpha = 0.6f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(Color(0xFF22C55E), CircleShape)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "LIVE WEB SERVER",
                            color = Color(0xFF22C55E),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }

            // Browser URL Address bar + Navigation Buttons (Back, Refresh, Lock)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Arrows (Back / Forward)
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = if (currentRoute != "login") SoftWhite else SoftGray,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable(enabled = currentRoute != "login") {
                            onNavigate("dashboard")
                        }
                )
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reload",
                    tint = SoftWhite,
                    modifier = Modifier
                        .size(18.dp)
                        .clickable { /* Simulate page reload */ }
                )

                // The Address Bar
                Surface(
                    color = Color(0xFF1E293B),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(30.dp),
                    border = RowDbStyle.borderStroke
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Secure Lock",
                            tint = Color(0xFF22C55E),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = dynamicUrl,
                            color = SoftWhite,
                            fontSize = 11.sp,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Add Bookmarks",
                            tint = SoftGray,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }

                // Chrome Extension Placeholder Dot
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(NeonCyan, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("AI", color = DeepBlue, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                }
            }
            
            HorizontalDivider(color = Color(0xFF1E293B), thickness = 1.dp)
        }

        // --- 2. THE WEBPAGE LAYOUT (TOP WEB NAVBAR + CONTENT VIEWER) ---
        Column(modifier = Modifier.weight(1f)) {
            // Web Header (Vercel-inspired tab bar)
            if (currentRoute != "login" && currentUserEmail != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkSurface)
                ) {
                    // Logo and User Account Info Block
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Code,
                                contentDescription = null,
                                tint = NeonCyan,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "DR CANVAS",
                                color = SoftWhite,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                color = NeonCyan.copy(alpha = 0.1f),
                                border = BorderStroke(1.dp, NeonCyan.copy(alpha = 0.3f)),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "WEB WORKSPACE",
                                    color = NeonCyan,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }

                        // Web User Profile Card & Sign Out Web-Link
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(
                                text = currentUserEmail,
                                color = SoftGray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "Sign Out",
                                color = Color(0xFFEF4444),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clickable { onLogout() }
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            )
                        }
                    }

                    // Vercel-style Navigation Bar Tab Bar Links
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        WebTabItem(
                            title = "Dashboard",
                            icon = Icons.Default.Dashboard,
                            isSelected = currentRoute == "dashboard",
                            onClick = { onNavigate("dashboard") }
                        )
                        WebTabItem(
                            title = "Modern Code IDE",
                            icon = Icons.Default.Code,
                            isSelected = currentRoute == "editor",
                            onClick = { onNavigate("editor") }
                        )
                        WebTabItem(
                            title = "AI Assistant Tutor",
                            icon = Icons.Default.AutoAwesome,
                            isSelected = currentRoute == "assistant",
                            onClick = { onNavigate("assistant") }
                        )
                        WebTabItem(
                            title = "Community Reviews",
                            icon = Icons.Default.RateReview,
                            isSelected = currentRoute == "reviews",
                            onClick = { onNavigate("reviews") }
                        )
                    }
                    
                    HorizontalDivider(color = Color(0xFF1E293B), thickness = 1.dp)
                }
            }

            // WebPage main content workspace
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DeepBlue)
            ) {
                content()
            }
        }
    }
}

@Composable
fun WebTabItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) NeonCyan else SoftGray,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                color = if (isSelected) SoftWhite else SoftGray,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            )
        }
        // Selection line
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(72.dp)
                .background(if (isSelected) NeonCyan else Color.Transparent)
        )
    }
}

object RowDbStyle {
    val borderWidth = 1.dp
    val borderStroke = BorderStroke(borderWidth, Color(0xFF1E293B))
}
