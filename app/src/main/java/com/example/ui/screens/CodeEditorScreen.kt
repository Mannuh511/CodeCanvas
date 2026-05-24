package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.NeonCyan

import com.example.ui.components.DeveloperFooter

@Composable
fun CodeEditorScreen(viewModel: MainViewModel) {
    var code by remember { mutableStateOf("// Write your code here...\nfun main() {\n    println(\"Hello Dr Canvas!\")\n}") }
    var filename by remember { mutableStateOf("main.kt") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 8.dp)) {
            OutlinedTextField(
                value = filename,
                onValueChange = { filename = it },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonCyan,
                    focusedTextColor = SoftWhite,
                    unfocusedTextColor = SoftWhite
                ),
                singleLine = true
            )
            Button(
                onClick = {
                    viewModel.saveProject(filename, code, "Kotlin")
                },
                modifier = Modifier.padding(start = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NeonCyan)
            ) {
                Icon(Icons.Default.Save, contentDescription = "Save", tint = DeepBlue)
                Text(" Save", color = DeepBlue)
            }
        }

        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonCyan,
                unfocusedBorderColor = com.example.ui.theme.DarkSurfaceVariant,
                focusedTextColor = SoftWhite,
                unfocusedTextColor = SoftWhite
            )
        )
        DeveloperFooter()
    }
}
