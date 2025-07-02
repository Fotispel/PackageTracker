package com.example.packagetracker

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ListItem
import androidx.compose.ui.graphics.Color
import com.composables.Moon
import com.composables.Sunny


@Composable
fun SettingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Τίτλος
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) {
                    Color.Black
                } else {
                    Color.White
                }
            ),
            border = BorderStroke(
                2.dp,
                if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Color.Black
                }
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            val isDarkMode = isSystemInDarkTheme()


            Column {
                ListItem(
                    headlineContent = { Text("Import Dara") },
                    leadingContent = { Icon(Icons.Default.ArrowForward, contentDescription = null) },
                    modifier = Modifier.clickable { /* action */ }
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                ListItem(
                    headlineContent = { Text("Export Data") },
                    leadingContent = { Icon(Icons.Default.ArrowBack, contentDescription = null) },
                    modifier = Modifier.clickable { /* action */ }
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                ListItem(
                    headlineContent = { Text("Theme") },
                    leadingContent = { Icon(
                        imageVector = if (isDarkMode) Moon else Sunny,
                        contentDescription = if (isDarkMode) "Dark Mode" else "Light Mode",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(25.dp)
                    )},
                    modifier = Modifier.clickable { /* action */ }
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                ListItem(
                    headlineContent = { Text("About") },
                    leadingContent = { Icon(Icons.Default.Info, contentDescription = null) },
                    modifier = Modifier.clickable { /* action */ }
                )
                Divider(
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                ListItem(
                    headlineContent = { Text("Share") },
                    leadingContent = { Icon(Icons.Default.Share, contentDescription = null) },
                    modifier = Modifier.clickable { /* action */ }
                )
            }
        }
    }
}
