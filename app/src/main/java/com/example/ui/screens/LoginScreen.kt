package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel
import com.example.ui.components.DeveloperFooter
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.NeonCyan

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, viewModel: MainViewModel) {
    var email by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var hasError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(DeepBlue, Color.Black)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Responsive Adaptive Canvas
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                val isWide = maxWidth > 640.dp

                if (isWide) {
                    // --- DESKTOP WEB PORTAL CONSOLE (DUAL-COLUMN) ---
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left Marketing SaaS Terminal Side
                        Column(
                            modifier = Modifier.weight(1.1f),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Code,
                                    contentDescription = null,
                                    tint = NeonCyan,
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = "Dr Canvas",
                                    color = SoftWhite,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }

                            Text(
                                text = "Next-Generation Cloud IDE & AI Programming Workspace",
                                color = SoftWhite,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                            Text(
                                text = "Accelerate your development cycle directly on the web. Create sandbox files, debug codebases, get on-demand tutoring from Gemini, and push direct reviews.",
                                color = SoftGray,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )

                            // Mock Web IDE Console Output Card
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF020617)),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(8.dp))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                        Box(modifier = Modifier.size(8.dp).background(Color(0xFFEF4444), CircleShape))
                                        Box(modifier = Modifier.size(8.dp).background(Color(0xFFF59E0B), CircleShape))
                                        Box(modifier = Modifier.size(8.dp).background(Color(0xFF22C55E), CircleShape))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("workspace-sandbox.sh", color = SoftGray, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text("drcanvas-web@sandbox:~# web-host --init", color = NeonCyan, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                                    Text("✔ Checking localized DB dependencies... (Offline mode: SQLite Room loaded)", color = Color(0xFF22C55E), fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                                    Text("✔ Synced with model: gemini-3.5-flash web-client", color = Color(0xFF22C55E), fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                                    Text("🚀 Dr Canvas SaaS environment ready at port 3000.", color = SoftWhite, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                                }
                            }
                        }

                        // Right Security Authenticators Panel
                        Column(modifier = Modifier.weight(0.9f)) {
                            LoginFormCard(
                                email = email,
                                pin = pin,
                                phone = phone,
                                hasError = hasError,
                                onEmailChange = { email = it; hasError = false },
                                onPinChange = { pin = it; hasError = false },
                                onPhoneChange = { phone = it; hasError = false },
                                onSubmit = {
                                    if (email.isNotBlank() && pin.isNotBlank() && phone.isNotBlank()) {
                                        viewModel.login(email, pin) { success ->
                                            if (success) onLoginSuccess() else hasError = true
                                        }
                                    }
                                }
                            )
                        }
                    }
                } else {
                    // --- PORTABLE RESPONSIVE WEB CARD (VERTICAL LAYOUT) ---
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Dr Canvas",
                            color = SoftWhite,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "Your Ultimate AI Programming Companion",
                            color = SoftGray,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        LoginFormCard(
                            email = email,
                            pin = pin,
                            phone = phone,
                            hasError = hasError,
                            onEmailChange = { email = it; hasError = false },
                            onPinChange = { pin = it; hasError = false },
                            onPhoneChange = { phone = it; hasError = false },
                            onSubmit = {
                                if (email.isNotBlank() && pin.isNotBlank() && phone.isNotBlank()) {
                                    viewModel.login(email, pin) { success ->
                                        if (success) onLoginSuccess() else hasError = true
                                    }
                                }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            DeveloperFooter()
        }
    }
}

@Composable
fun LoginFormCard(
    email: String,
    pin: String,
    phone: String,
    hasError: Boolean,
    onEmailChange: (String) -> Unit,
    onPinChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSurface.copy(alpha = 0.9f)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Workspace Authentication",
                color = SoftWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Sign in or register your dynamic developer seat",
                color = SoftGray,
                fontSize = 11.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 20.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email Handle") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = SoftGray) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonCyan,
                    focusedLabelColor = NeonCyan,
                    focusedTextColor = SoftWhite,
                    unfocusedTextColor = SoftWhite,
                    unfocusedBorderColor = Color(0xFF1E293B)
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(14.dp))
            OutlinedTextField(
                value = pin,
                onValueChange = onPinChange,
                label = { Text("Email Password / PIN") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = SoftGray) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonCyan,
                    focusedLabelColor = NeonCyan,
                    focusedTextColor = SoftWhite,
                    unfocusedTextColor = SoftWhite,
                    unfocusedBorderColor = Color(0xFF1E293B)
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(14.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = onPhoneChange,
                label = { Text("Mobile Phone Number") },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = SoftGray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonCyan,
                    focusedLabelColor = NeonCyan,
                    focusedTextColor = SoftWhite,
                    unfocusedTextColor = SoftWhite,
                    unfocusedBorderColor = Color(0xFF1E293B)
                ),
                singleLine = true
            )

            if (hasError) {
                Text(
                    text = "Invalid credentials. Please fill all fields.",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonCyan),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Secure Portal Login", color = DeepBlue, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Account creation auto-registers seat instantly.",
                color = SoftGray,
                fontSize = 10.sp
            )
        }
    }
}

val SoftWhite = Color(0xFFE2E8F0)
val SoftGray = Color(0xFF94A3B8)
